package lk.nexttravel.apigateway.service.impl;

import lk.nexttravel.apigateway.advice.util.InternalServerException;
import lk.nexttravel.apigateway.advice.util.UnauthorizeException;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.persistence.UserRepository;
import lk.nexttravel.apigateway.service.SequenceGeneratorService;
import lk.nexttravel.apigateway.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:39
 */
public class SystemUserServiceImpl implements SystemUserService
{
//    @Autowired
//    private MailService mailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    APIGatewayJwtAccessTokenServiceFrontend APIGatewayJwtAccessTokenServiceFrontend;

    @Autowired
    RefreshTokenServiceFrontend refreshTokenServiceFrontend;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    TransactionCordinator transactionCordinator;

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;


    @Override
    public ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username) {
        try{
            if(userRepository.existsByName(username)){
                //Existed
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED)
                                .repd_msg("This User is exists!")
                                .token(null)
                                .data(null)
                                .build()
                        ,
                        HttpStatus.ACCEPTED
                );
            }else{
                //not Existed
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_THIS_USER_NOT_REGISTERED_YET)
                                .repd_msg("This User not exists!")
                                .token(null)
                                .data(null)
                                .build()
                        ,
                        HttpStatus.OK
                );
            }
        }catch (Exception e){
            throw new InternalServerException("Username Check Exception Internal!");
        }
    }

    @Override
    public ResponseEntity<RespondDTO> checkUsernameAndSendOTP(String username) {
        try{
            if(userRepository.existsByName(username)){
                //-------create otp--------
                Random random = new Random();
                StringBuilder otp = new StringBuilder();
                // Generate four random numeric digits
                for (int i = 0; i < 4; i++) {
                    int digit = random.nextInt(10); // Generates a random number between 0 and 9
                    otp.append(digit);
                }

                //save otp on DB
                Optional<User> user = userRepository.findUserByName(username);
                if(user.isPresent()){
                    user.get().setMail_otp(otp.toString());
                    userRepository.save(user.get()); //save in DB
                }else{
                    throw new InternalServerException("Username Check Exception Internal!");
                }

                //send mail
                boolean isSentMail = mailService.sendEmailForSendOTPRecoverPassword(user.get().getEmail(), user.get().getName(), otp.toString());
                if(!isSentMail){
                    throw new InternalServerException("Username Check Exception Internal!");
                }

                //Existed
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED)
                                .repd_msg("This User is exists!")
                                .token(null)
                                .data(null)
                                .build()
                        ,
                        HttpStatus.ACCEPTED
                );
            }else{
                //not Existed
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_THIS_USER_NOT_REGISTERED_YET)
                                .repd_msg("This User not exists!")
                                .token(null)
                                .data(null)
                                .build()
                        ,
                        HttpStatus.OK
                );
            }
        }catch (Exception e){
            throw new InternalServerException("Username Check Exception Internal!");
        }    return null;
    }

    @Override
    public ResponseEntity<RespondDTO> verifyUsernameWithOTP(String username, String otp) {
        Optional<User> user = userRepository.findUserByName(username);

        try{
            if(user.isPresent()){
                if( user.get().getName().equals(username)
                        &&
                        user.get().getMail_otp().equals(otp)
                ){
                    //Existed
                    return new ResponseEntity<RespondDTO>(
                            RespondDTO.builder()
                                    .rspd_code(RespondCodes.Respond_USERNAME_AND_OTP_VERIFIED)
                                    .repd_msg("This User is exists!")
                                    .token(null)
                                    .data(null)
                                    .build()
                            ,
                            HttpStatus.ACCEPTED
                    );
                }else{
                    throw new InternalServerException("Username OTP Check Exception Internal!");
                }
            }else{
                throw new InternalServerException("Username Check Exception Internal!");
            }
        }catch (Exception e){
            throw new InternalServerException("Username Check Exception Internal!");
        }
    }

    @Override
    public ResponseEntity<RespondDTO> userLoginWithRecoverdPassword(String username, String otp, String password) {
        try{
            Optional<User> user=userRepository.findUserByName(username);
            if(user.isPresent()){
                //check Username password matched
                if(
                        user.get().getName().equals(username)
                                &&
                                user.get().getMail_otp().equals(otp)
                ){
                    //if matched
                    //delete generated otp
                    user.get().setMail_otp("");

                    //set new password
                    user.get().setPassword(passwordEncoder.encode(password));

                    //save user into DB with new values
                    userRepository.save(user.get());

                    //Access Token Create Get
                    String newAccessToken = APIGatewayJwtAccessTokenServiceFrontend.generateToken(user.get().getName()); //create and get JWT access token

                    //UserRefreshToken Save On Gateway DB
                    String newRefreshToken = refreshTokenServiceFrontend.createRefreshToken(user.get()); //create get and save refresh token

                    FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                            .access_username(user.get().getName())
                            .access_jwt_token(newAccessToken) //create access token and assign it
                            .access_refresh_token(newRefreshToken)  //create refresh token and save and assign it
                            .build();

                    return new ResponseEntity<RespondDTO> (
                            RespondDTO.builder()
                                    .rspd_code(RespondCodes.Respond_NEW_PASSWORD_CREATED_AND_LOGIN_SUCCEED)
                                    .token(frontendTokenDTO)
                                    .data(user.get().getRole_type())
                                    .build()
                            ,
                            HttpStatus.CREATED);
                }else{
                    throw new InternalServerException("Username Check Exception Internal!");
                }
            }else {
                throw new InternalServerException("Username Check Exception Internal!");
            }
        }catch (Exception e){
            throw new InternalServerException("Username Check Exception Internal!");
        }  return null;
    }

    @Override
    public ResponseEntity<RespondDTO> saveNewGuestUser(UserSignupDTO userSignupDTO) {
        ArrayList<TransactionDTO> transactionDTOArrayList = new ArrayList<>();
        try {
            String id = "U00"+sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
            String password = passwordEncoder.encode(userSignupDTO.getSignup_password());

            Optional<User> savedUser;
            if(!userRepository.existsByName(userSignupDTO.getSignup_name())){
                //User Save On Gateway DB -task 1
                savedUser = Optional.of(
                        userRepository.save(
                                User.builder()
                                        .id(id)
                                        .name(userSignupDTO.getSignup_name())
                                        .email(userSignupDTO.getSignup_email())
                                        .password(password)
                                        .role_type(RoleTypes.ROLE_CLIENT)
                                        .transaction_state(RespondCodes.PENDING)
                                        .build())
                );
            }else {
                throw new DuplicateException("This user already saved!");
            }


            //send data into transaction Coordinator for prepare

            transactionDTOArrayList.add(
                    TransactionDTO.builder()      //send data into User Service - task2
                            .url(RqRpURLs.User_Service_New_Client_Save_Signup) //url
                            .httpMethod(HttpMethod.POST)  //http method
                            .data(
                                    UserReqNewClientSaveDTO.builder()
                                            .token( apiGatewayJwtAccessTokenServiceBackend.generateToken() ) //create a session token to connect with microservice check this request is valid request
                                            .id(id)
                                            .address(userSignupDTO.getSignup_address())
                                            .nic_or_passport(userSignupDTO.getSignup_nic_or_passport())
                                            .profile_image(userSignupDTO.getSignup_profile_image())
                                            .name_with_initial(userSignupDTO.getSignup_name_with_initial())
                                            .build()
                            )
                            .build()
            );
            //add transactiondto arraylist for commit
            boolean isAllMicroServiceTasksCommited =false;
            isAllMicroServiceTasksCommited = transactionCordinator.preparePhaseForCreate(transactionDTOArrayList);

            if(
                    savedUser.isPresent() && isAllMicroServiceTasksCommited
            ){
                //commit user
                savedUser.get().setTransaction_state(RespondCodes.COMMITED);
                userRepository.save(savedUser.get());

                //commit
                transactionCordinator.commitPhaseForCreate(transactionDTOArrayList);

                //Access Token Create Get
                String newAccessToken = APIGatewayJwtAccessTokenServiceFrontend.generateToken(userSignupDTO.getSignup_name()); //create and get JWT access token

                //UserRefreshToken Save On Gateway DB
                String newRefreshToken = refreshTokenServiceFrontend.createRefreshToken(savedUser.get()); //create get and save refresh token

                FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                        .access_username(savedUser.get().getName())
                        .access_jwt_token(newAccessToken) //create access token and assign it
                        .access_refresh_token(newRefreshToken)  //create refresh token and save and assign it
                        .build();

                //send mail
                mailService.sendEmailForNewUserSignup(userSignupDTO.getSignup_email(), userSignupDTO.getSignup_name());

                //----------------------------------------------return if all are done
                return new ResponseEntity<RespondDTO> (
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                .token(frontendTokenDTO)
                                .data(savedUser.get().getRole_type())
                                .build()
                        ,
                        HttpStatus.CREATED);
            }else {
                //abrot User
                userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

                //abrot Client
                transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

                throw new InternalServerException("This User not saved!");
            }
        } catch (Exception e){

            //abrot User
            userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

            //abrot Client
            transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

            throw new InternalServerException("This User not saved!");
        }


    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> checkUsernamePasswordUserLogin(String username, String password) {
        try{

            Optional<User> user=userRepository.findUserByName(username);
            System.out.println(passwordEncoder.matches( password, user.get().getPassword()) + " password");
            if(user.isPresent()){
                //check Username password matched
                if(
                        user.get().getName().equals(username)
                                &&
                                passwordEncoder.matches( password, user.get().getPassword())
                ){
                    //if matched
                    //Access Token Create Get
                    String newAccessToken = APIGatewayJwtAccessTokenServiceFrontend.generateToken(user.get().getName()); //create and get JWT access token

                    //UserRefreshToken Save On Gateway DB
                    String newRefreshToken = refreshTokenServiceFrontend.createRefreshToken(user.get()); //create get and save refresh token

                    FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                            .access_username(user.get().getName())
                            .access_jwt_token(newAccessToken) //create access token and assign it
                            .access_refresh_token(newRefreshToken)  //create refresh token and save and assign it
                            .build();

                    return Mono.just(new ResponseEntity<RespondDTO> (
                            RespondDTO.builder()
                                    .rspd_code(RespondCodes.Respond_PASSWORD_MATCHED)
                                    .token(frontendTokenDTO)
                                    .data(user.get().getRole_type())
                                    .build()
                            ,
                            HttpStatus.CREATED));
                }else{
                    return Mono.error(new DuplicateException("ddd"));
                }
            }else {
                return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Password invalid"));
            }
        }catch (Exception e){
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Password invalid"));
        }

    }

    @Override
    public void saveNewAdminUserOnlyTesting(UserSignupDTO userSignupDTO, RoleTypes roleTypes) {
        ArrayList<TransactionDTO> transactionDTOArrayList = new ArrayList<>();
        Optional<User> savedUser = null;
        try {
            String id = "U00"+sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
            String password = passwordEncoder.encode(userSignupDTO.getSignup_password());

            if(!userRepository.existsByName(userSignupDTO.getSignup_name())){
                //User Save On Gateway DB -task 1
                savedUser = Optional.of(
                        userRepository.save(
                                User.builder()
                                        .id(id)
                                        .name(userSignupDTO.getSignup_name())
                                        .email(userSignupDTO.getSignup_email())
                                        .password(password)
                                        .role_type(roleTypes)
                                        .transaction_state(RespondCodes.PENDING)
                                        .build())
                );
            }else {

            }


            //send data into transaction Coordinator for prepare

            transactionDTOArrayList.add(
                    TransactionDTO.builder()      //send data into User Service - task2
                            .url(RqRpURLs.User_Service_New_Admin_Save_Signup) //url
                            .httpMethod(HttpMethod.POST)  //http method
                            .data(
                                    UserReqNewClientSaveDTO.builder()
                                            .token( apiGatewayJwtAccessTokenServiceBackend.generateToken() ) //create a session token to connect with microservice check this request is valid request
                                            .id(id)
                                            .address(userSignupDTO.getSignup_address())
                                            .nic_or_passport(userSignupDTO.getSignup_nic_or_passport())
                                            .profile_image(userSignupDTO.getSignup_profile_image())
                                            .name_with_initial(userSignupDTO.getSignup_name_with_initial())
                                            .build()
                            )
                            .build()
            );
            //add transactiondto arraylist for commit
            boolean isAllMicroServiceTasksCommited =false;
            isAllMicroServiceTasksCommited = transactionCordinator.preparePhaseForCreate(transactionDTOArrayList);

            if(
                    savedUser.isPresent() && isAllMicroServiceTasksCommited
            ){
                //commit user
                savedUser.get().setTransaction_state(RespondCodes.COMMITED);
                userRepository.save(savedUser.get());

                //commit
                transactionCordinator.commitPhaseForCreate(transactionDTOArrayList);

                //Access Token Create Get
                String newAccessToken = APIGatewayJwtAccessTokenServiceFrontend.generateToken(userSignupDTO.getSignup_name()); //create and get JWT access token

                //UserRefreshToken Save On Gateway DB
                String newRefreshToken = refreshTokenServiceFrontend.createRefreshToken(savedUser.get()); //create get and save refresh token

                FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                        .access_username(savedUser.get().getName())
                        .access_jwt_token(newAccessToken) //create access token and assign it
                        .access_refresh_token(newRefreshToken)  //create refresh token and save and assign it
                        .build();

                //send mail
                mailService.sendEmailForNewUserSignup(userSignupDTO.getSignup_email(), userSignupDTO.getSignup_name());

                //----------------------------------------------return if all are done
                new ResponseEntity<RespondDTO> (
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                .token(frontendTokenDTO)
                                .data(savedUser.get().getRole_type())
                                .build()
                        ,
                        HttpStatus.CREATED);
            }else {
                //abrot User
                userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

                //abrot Client
                transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

            }
        } catch (Exception e){

            //abrot User
            userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

            //abrot Client
            transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

        }


    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> userGetProfileData(String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();


            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            System.out.println(internalFrontendSecurityCheckDTO.toString());
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_CLIENT)
            ) {

                //get data using restcontroller
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                User user = userRepository.findUserByName(frontendTokenDTO.getAccess_username()).get();

                System.out.println(user.getId());
                ResponseEntity<ReqProfileDataAdminsDTO> reqProfileDataAdminsDTOResponseEntity = restTemplate.exchange(
                        "http://localhost:1020/api/client/get-client-data?id=" + user.getId() + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
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
}
