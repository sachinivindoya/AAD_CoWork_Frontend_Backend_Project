package lk.nexttravel.apigateway.api;

import lk.nexttravel.apigateway.advice.util.InvalidInputException;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.service.SystemUserService;
import lk.nexttravel.apigateway.service.security.Authenticate_Authorize_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:46
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:63342/")
public class UserController {

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    //checkUsername
    @GetMapping(value = "/ischeck-username")
    public Mono<ResponseEntity<RespondDTO>> checkUsername(@RequestParam("username") @NonNull String username) {
        if (username.matches("^[a-zA-Z0-9_.-]{5,30}$")) {   //check Username Regax
            return Mono.just( systemUserService.ischeckUsernameAlreadyTaken(username) );
        } else {
            return Mono.error( new InvalidInputException("Username is invalid!") );
        }
    }

    //checkUsername and send otp
    @GetMapping(value = "/ischeck-username-and-send-otp")
    public Mono<ResponseEntity<RespondDTO>> checkUsernameAndSendOTP(@RequestParam("username") @NonNull String username) {
        if (username.matches("^[a-zA-Z0-9_.-]{5,30}$")) {   //check Username Regax
            return Mono.just( systemUserService.checkUsernameAndSendOTP(username) );
        } else {
            return Mono.error( new InvalidInputException("Username is invalid!") );
        }
    }

    //verify Username with there OTP
    @GetMapping(value = "/isverify-username-with-otp")
    public Mono<ResponseEntity<RespondDTO>> verifyUsernameWithOTP(
            @RequestParam("username") @NonNull String username
            ,
            @RequestParam("otp") @NonNull String otp
    ) {
        if (username.matches("^[a-zA-Z0-9_.-]{5,30}$")
                &&
                otp.matches("\\b\\d{4}\\b")
        ) {   //check Username Regax
            return Mono.just( systemUserService.verifyUsernameWithOTP(username, otp) );
        } else {
            return Mono.error( new InvalidInputException("Username or otp is invalid!") );
        }
    }

    //save user
    @PostMapping(value = "/signup-guestuser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<RespondDTO>> saveNewGuestUser(@RequestPart("signup_name") @NonNull String name,
                                                             @RequestPart("signup_name_with_initial") @NonNull String nameWithInitial,
                                                             @RequestPart("signup_email") @NonNull String email,
                                                             @RequestPart("signup_password") @NonNull String password,
                                                             @RequestPart("signup_nic_or_passport") @NonNull String nicOrPassport,
                                                             @RequestPart("signup_address") @NonNull String addres,
                                                             @RequestPart("signup_profile_image") String image) {

        if (name.matches("^[a-zA-Z0-9_.-]{5,30}$")) {                                         //check Username Regax
            if (nameWithInitial.matches("([A-Z])\\w+\\s([A-Z])\\w*\\s*([A-Z])*(?=,*)")) {     //check Name with Initial Regax
                if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {     //check Email Regax
                    if (password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {         //check Password Regax
                        if (nicOrPassport.matches("^[a-zA-Z0-9_-]+\\S{8,11}$")) {             //check Nic or Passport Regax
                            if (addres.matches("^\\S+\\s*[a-zA-Z0-9,.-]+\\S{0,48}$")) {       //check Address Regax
                                if (image != null) {

                                    return Mono.just(
                                            systemUserService.saveNewGuestUser(
                                                    UserSignupDTO.builder()
                                                            .signup_name(name)
                                                            .signup_name_with_initial(nameWithInitial)
                                                            .signup_address(addres)
                                                            .signup_email(email)
                                                            .signup_nic_or_passport(nicOrPassport)
                                                            .signup_password(password)
                                                            .signup_profile_image(image)
                                                            .build()
                                            )
                                    );

                                } else {
                                    throw new InvalidInputException("Image data is invalid!");
                                }
                            } else {
                                throw new InvalidInputException("Address data is invalid!");
                            }
                        } else {
                            throw new InvalidInputException("NIC or Passport data is invalid!");
                        }
                    } else {
                        throw new InvalidInputException("Password data is invalid!");
                    }
                } else {
                    throw new InvalidInputException("Email is invalid!");
                }

            } else {
                throw new InvalidInputException("Name with initialis invalid!");
            }
        } else {
            throw new InvalidInputException("Username is invalid!");
        }
    }

    //user login
    @GetMapping(value = "/user-login")
    public Mono<ResponseEntity<RespondDTO>> userLogin(
            @RequestParam("username") @NonNull String username ,
            @RequestParam("password") @NonNull String password
    ) {

        if ( username.matches("^[a-zA-Z0-9_.-]{5,30}$") &&                         //check Username Regax
                password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")         //check Password Regax
        ) {
            return systemUserService.checkUsernamePasswordUserLogin(username,password);

        } else {
            return Mono.error( new InvalidInputException(""));
        }
    }

    //verify Username with there OTP
    @GetMapping(value = "/user-login-with-recoverd-password")
    public Mono<ResponseEntity<RespondDTO>> userLoginWithRecoverdPassword(
            @RequestParam("username") @NonNull String username
            ,
            @RequestParam("otp") @NonNull String otp
            ,
            @RequestParam("password") @NonNull String password
    ) {
        if (username.matches("^[a-zA-Z0-9_.-]{5,30}$")
                &&
                otp.matches("\\b\\d{4}\\b")
                &&
                password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")
        ) {   //check Username Regax
            return Mono.just( systemUserService.userLoginWithRecoverdPassword(username, otp, password) );
        } else {
            return Mono.error( new InvalidInputException("Username or otp is invalid!") );
        }
    }

    //user login
    @GetMapping(value = {"/user-get-profile-data"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userGetProfileData(
            @RequestParam("access_username") @NonNull String access_username
            ,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token
            ,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        System.out.println(" donee");
        return systemUserService.userGetProfileData(
                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }

}
