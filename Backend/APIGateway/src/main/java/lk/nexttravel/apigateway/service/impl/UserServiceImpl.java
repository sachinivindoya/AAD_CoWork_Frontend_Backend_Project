package lk.nexttravel.apigateway.service.impl;

import lk.nexttravel.apigateway.advice.util.InternalServerException;
import lk.nexttravel.apigateway.advice.util.UnauthorizeException;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.apigateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.apigateway.dto.user.AdminDTO;
import lk.nexttravel.apigateway.dto.user.ReqProfileDataAdminsDTO;
import lk.nexttravel.apigateway.dto.user.UserAdminDTO;
import lk.nexttravel.apigateway.dto.user.UserReqProfileDataDTO;
import lk.nexttravel.apigateway.entity.RefreshToken;
import lk.nexttravel.apigateway.entity.User;
import lk.nexttravel.apigateway.persistence.RefreshTokenRepository;
import lk.nexttravel.apigateway.persistence.UserRepository;
import lk.nexttravel.apigateway.service.UserService;
import lk.nexttravel.apigateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.apigateway.service.security.util.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.apigateway.util.RespondCodes;
import lk.nexttravel.apigateway.util.RoleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:45
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    @Autowired
    APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    RefreshTokenRepository refreshTokenRepository;


    @Override
    public Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_USER)
            ) {
                //get data using restcontroller
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                User user = userRepository.findUserByName(frontendTokenDTO.getAccess_username()).get();

                ResponseEntity<ReqProfileDataAdminsDTO> reqProfileDataAdminsDTOResponseEntity = restTemplate.exchange(
                        "http://localhost:1020/api/admin/user-admin-get-profile-data?id=" + user.getId() + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                        HttpMethod.GET,
                        entity,
                        ReqProfileDataAdminsDTO.class
                );
                //send to front
                return Mono.just(new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_SUCCESS)
                                .data(
                                        UserReqProfileDataDTO.builder()
                                                .name(user.getName())
                                                .name_with_initial(reqProfileDataAdminsDTOResponseEntity.getBody().getName_with_initial())
                                                .address(reqProfileDataAdminsDTOResponseEntity.getBody().getAddress())
                                                .email(user.getEmail())
                                                .profile_image(reqProfileDataAdminsDTOResponseEntity.getBody().getProfile_image())
                                                .nic_or_passport(reqProfileDataAdminsDTOResponseEntity.getBody().getNic_or_passport())
                                                .build()
                                )
                                .token(
                                        FrontendTokenDTO.builder()
                                                .access_username(internalFrontendSecurityCheckDTO.getUsername())
                                                .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                                                .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                                                .build()
                                )
                                .build()
                        ,
                        HttpStatus.OK
                ));
            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            throw new InternalServerException("Internal server Error");
        }    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> getAllAdminsBySearch(String search_keyword, String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_USER)
            ) {

                ArrayList<UserAdminDTO> userAdminDTOS = new ArrayList<>();

                //get on User DB admin datas
                ArrayList<User> userArrayList = userRepository.findAllByNameContains(search_keyword);

                //fill and add
                for (User user:userArrayList){
                    UserAdminDTO userAdminDTO = new UserAdminDTO();
                    userAdminDTO.setId(user.getId());
                    userAdminDTO.setName(user.getName());
                    userAdminDTO.setEmail(user.getEmail());
                    userAdminDTO.setRole_type(user.getRole_type());

                    //get data using restcontroller
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                    ResponseEntity<AdminDTO> adminDTOResponseEntity = restTemplate.exchange(
                            "http://localhost:1020/api/admin/get-admin-data?search_keyword=" + user.getId() + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                            HttpMethod.GET,
                            entity,
                            AdminDTO.class
                    );
                    userAdminDTO.setSignup_name_with_initial(adminDTOResponseEntity.getBody().getSignup_name_with_initial());
                    userAdminDTO.setNic_or_passport(adminDTOResponseEntity.getBody().getNic_or_passport());
                    userAdminDTO.setAddress(adminDTOResponseEntity.getBody().getAddress());
                    userAdminDTO.setSalary(adminDTOResponseEntity.getBody().getSalary());
                    userAdminDTO.setProfile_image(adminDTOResponseEntity.getBody().getProfile_image());

                    //add dto to list
                    userAdminDTOS.add(userAdminDTO);
                }

                //send to front
                return Mono.just(new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_SUCCESS)
                                .data(userAdminDTOS)
                                .token(
                                        FrontendTokenDTO.builder()
                                                .access_username(internalFrontendSecurityCheckDTO.getUsername())
                                                .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                                                .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                                                .build()
                                )
                                .build()
                        ,
                        HttpStatus.OK
                ));

            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            System.out.println("internal server error");
            throw new InternalServerException("Internal server Error");
        }
    }




    @Override
    public Mono<ResponseEntity<RespondDTO>> requestToLogout(String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);

            System.out.println("**********"+internalFrontendSecurityCheckDTO.isAccesssible());
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()

            ) {

                //delelte saved refresh token on DB
                RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenById(userRepository.findUserByName(access_username).get().getId()).get();
                refreshToken.setToken("");

                //save
                refreshTokenRepository.save(refreshToken);

                //send to front
                return Mono.just(new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_SUCCESS)
                                .data(null)
                                .token(null)
                                .build()
                        ,
                        HttpStatus.OK
                ));
            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            throw new InternalServerException("Internal server Error");
        }

    }
}
