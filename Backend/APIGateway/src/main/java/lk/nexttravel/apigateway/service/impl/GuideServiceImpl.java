package lk.nexttravel.apigateway.service.impl;

import lk.nexttravel.apigateway.advice.util.InternalServerException;
import lk.nexttravel.apigateway.advice.util.UnauthorizeException;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.TransactionDTO;
import lk.nexttravel.apigateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.apigateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.apigateway.dto.guide.GuideDTO;
import lk.nexttravel.apigateway.dto.guide.ReqNewGuideSaveDTO;
import lk.nexttravel.apigateway.dto.user.ReqProfileDataAdminsDTO;
import lk.nexttravel.apigateway.dto.user.ReqUpdateGuideAdminDTO;
import lk.nexttravel.apigateway.dto.user.UserReqProfileDataDTO;
import lk.nexttravel.apigateway.entity.User;
import lk.nexttravel.apigateway.persistence.UserRepository;
import lk.nexttravel.apigateway.service.GuideService;
import lk.nexttravel.apigateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.apigateway.service.security.util.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.apigateway.service.security.util.APIGatewayJwtAccessTokenServiceFrontend;
import lk.nexttravel.apigateway.service.security.util.RefreshTokenServiceFrontend;
import lk.nexttravel.apigateway.service.transaction.TransactionCordinator;
import lk.nexttravel.apigateway.util.RespondCodes;
import lk.nexttravel.apigateway.util.RoleTypes;
import lk.nexttravel.apigateway.util.RqRpURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:29
 */
@Service
public class GuideServiceImpl implements GuideService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    @Autowired
    APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    TransactionCordinator transactionCordinator;

    @Autowired
    APIGatewayJwtAccessTokenServiceFrontend apiGatewayJwtAccessTokenServiceFrontend;

    @Autowired
    RefreshTokenServiceFrontend refreshTokenServiceFrontend;

    private final RestTemplate newrestTemplate = new RestTemplate();

    @Override
    public Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();


            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            System.out.println(internalFrontendSecurityCheckDTO.toString());
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
            ) {

                //get data using restcontroller
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                User user = userRepository.findUserByName(frontendTokenDTO.getAccess_username()).get();

                System.out.println(user.getId());
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
                                                .id(user.getId())
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

            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }
    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> UserAdminUpdateProfileData(String id, String username, String address, String email, String nic, String password, String nameinitial, String profileImage_base64String, String access_username, String access_jwt_token, String access_refresh_token) {
        ArrayList<TransactionDTO> transactionDTOArrayList = new ArrayList<>(); // for transactions
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
            ) {

                String encodedpassword = passwordEncoder.encode(password);
                System.out.println(password);

                Optional<User> savedUser = userRepository.findUserByName(username);

                if(savedUser.isPresent()){
                    //User Save On Gateway DB -task 1
                    savedUser.get().setId(id);
                    savedUser.get().setPassword(encodedpassword);
                    savedUser.get().setEmail(email);
                    savedUser.get().setName(username);
                    savedUser.get().setTransaction_state(RespondCodes.PENDING);
                    userRepository.save(savedUser.get());
                }else {
                    throw new Exception("Not found");
                }

                //send data into transaction Coordinator for prepare
                transactionDTOArrayList.add(
                        TransactionDTO.builder()      //send data into User Service - task2
                                .url(RqRpURLs.User_Service_Guide_Update) //url
                                .httpMethod(HttpMethod.POST)  //http method
                                .data(
                                        ReqUpdateGuideAdminDTO.builder()
                                                .id(id)
                                                .name_with_initial(nameinitial)
                                                .nic_or_passport(nic)
                                                .address(address)
                                                .profile_image(profileImage_base64String)
                                                .transaction_state(RespondCodes.PENDING)
                                                .token(apiGatewayJwtAccessTokenServiceBackend.generateToken())
                                                .build()
                                )
                                .build()
                );
                //add transactiondto arraylist for commit
                boolean isAllMicroServiceTasksCommited =false;
                isAllMicroServiceTasksCommited = transactionCordinator.preparePhaseForUpdate(transactionDTOArrayList);

                if(
                        savedUser.isPresent() && isAllMicroServiceTasksCommited
                ){
                    //commit user
                    savedUser.get().setTransaction_state(RespondCodes.COMMITED);
                    userRepository.save(savedUser.get());

                    //commit
                    transactionCordinator.commitPhaseForUpdate(transactionDTOArrayList);

                    FrontendTokenDTO newfrontendTokenDTO = FrontendTokenDTO.builder()
                            .access_username(savedUser.get().getName())
                            .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                            .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                            .build();

                    //----------------------------------------------return if all are done
                    return Mono.just(
                            new ResponseEntity<RespondDTO> (
                                    RespondDTO.builder()
                                            .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                            .token(newfrontendTokenDTO)
                                            .data(null)
                                            .build()
                                    ,
                                    HttpStatus.CREATED)
                    );
                }else {

                    //abrot User
                    userRepository.delete(userRepository.findUserByName(username).get());

                    //abrot Client
                    transactionCordinator.rollbackPhaseForUpdate(transactionDTOArrayList);

                    throw new InternalServerException("This User not saved!");
                }

            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> createNewGuide(String name, String address, String remarks, String experience, String nic, String nicFrontView, String nicRearView, String tell, String gender, String dob, String image, String perdayFee, String accessUsername, String accessToken, String refreshToken) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(accessToken).access_username(accessUsername).access_refresh_token(refreshToken).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
            ) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        RqRpURLs.Guide_Service_New_Guide_save,
                        HttpMethod.POST,
                        new HttpEntity<Object> (
                                ReqNewGuideSaveDTO.builder()
                                        .name(name)
                                        .remarks(remarks)
                                        .experience(Integer.parseInt(experience))
                                        .nic(nic)
                                        .nic_front_view(nicFrontView)
                                        .nic_rear_view(nicRearView)
                                        .tell(tell)
                                        .gender(gender)
                                        .dob(dob)
                                        .image(image)
                                        .address(address)
                                        .perday_fee(Integer.parseInt(perdayFee))
                                        .token(apiGatewayJwtAccessTokenServiceBackend.generateToken())
                                        .build()
                                ,
                                headers
                        ),
                        String.class
                );

                if(responseEntity.getStatusCode()==HttpStatus.CREATED){
                    FrontendTokenDTO newfrontendTokenDTO = FrontendTokenDTO.builder()
                            .access_username(accessUsername)
                            .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                            .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                            .build();

                    //----------------------------------------------return if all are done
                    return Mono.just(
                            new ResponseEntity<RespondDTO> (
                                    RespondDTO.builder()
                                            .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                            .token(newfrontendTokenDTO)
                                            .data(null)
                                            .build()
                                    ,
                                    HttpStatus.CREATED)
                    );
                }else {
                    throw new Exception("Server error");
                }
            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }
    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> getAllGuides(String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
            ) {

                ArrayList<GuideDTO> guideDTOS = new ArrayList<>();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(null, headers);

                ParameterizedTypeReference<ArrayList<GuideDTO>> typeRef = new ParameterizedTypeReference<ArrayList<GuideDTO>>() {};

                ResponseEntity<ArrayList<GuideDTO>> guideDTOEntity = restTemplate.exchange(
                        "http://localhost:1030/api/guide/getall-guides?token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                        HttpMethod.GET,
                        entity,
                        typeRef // Use the ParameterizedTypeReference to specify the collection type
                );

                guideDTOS = guideDTOEntity.getBody();

                return Mono.just(new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_SUCCESS)
                                .data(guideDTOS)
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
    public Mono<ResponseEntity<RespondDTO>> updateGuide(String id, String name, String address, String remarks, String experience, String nic, String nicFrontView, String nicRearView, String tell, String gender, String dob, String image, String perdayFee, String accessUsername, String accessToken, String refreshToken) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(accessToken).access_username(accessUsername).access_refresh_token(refreshToken).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
            ) {

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        RqRpURLs.Guide_Service_New_Guide_save,
                        HttpMethod.PUT,
                        new HttpEntity<Object> (
                                ReqNewGuideSaveDTO.builder()
                                        .id(id)
                                        .name(name)
                                        .remarks(remarks)
                                        .experience(Integer.parseInt(experience))
                                        .nic(nic)
                                        .nic_front_view(nicFrontView)
                                        .nic_rear_view(nicRearView)
                                        .tell(tell)
                                        .gender(gender)
                                        .dob(dob)
                                        .image(image)
                                        .address(address)
                                        .perday_fee(Integer.parseInt(perdayFee))
                                        .token(apiGatewayJwtAccessTokenServiceBackend.generateToken())
                                        .build()
                                ,
                                headers
                        ),
                        String.class
                );

                if(responseEntity.getStatusCode()==HttpStatus.CREATED){
                    FrontendTokenDTO newfrontendTokenDTO = FrontendTokenDTO.builder()
                            .access_username(accessUsername)
                            .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                            .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                            .build();

                    //----------------------------------------------return if all are done
                    return Mono.just(
                            new ResponseEntity<RespondDTO> (
                                    RespondDTO.builder()
                                            .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                            .token(newfrontendTokenDTO)
                                            .data(null)
                                            .build()
                                    ,
                                    HttpStatus.CREATED)
                    );
                }else {
                    throw new Exception("Server error");
                }
            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }
    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> deleteGuide(String id, String access_username, String access_jwt_token, String access_refresh_token) {
        {
            try{
                FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();


                InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
                System.out.println(internalFrontendSecurityCheckDTO.toString());
                if(
                        internalFrontendSecurityCheckDTO.isAccesssible()
                                &&
                                internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
                ) {

                    //get data using restcontroller
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                    ResponseEntity<String> ResponseEntity = restTemplate.exchange(
                            "http://localhost:1030/api/guide/delete-guide?id=" + id + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                            HttpMethod.DELETE,
                            entity,
                            String.class
                    );
                    if(ResponseEntity.getStatusCode()== HttpStatus.OK){
                        //send to front
                        return Mono.just(new ResponseEntity<RespondDTO>(
                                RespondDTO.builder()
                                        .rspd_code(RespondCodes.Respond_SUCCESS)
                                        .data(
                                                null
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
                        throw new Exception("not deleted");
                    }

                }else {
                    return Mono.error(new UnauthorizeException("Unauthorized request"));
                }
            }catch (Exception e){

                return Mono.error(new InternalServerException("Internal Server error!"+e));
            }
        }
    }
}
