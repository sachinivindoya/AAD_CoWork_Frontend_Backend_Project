package lk.nexttravel.apigateway.api;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 10:50
 */


import lk.nexttravel.apigateway.advice.util.InvalidDataEntryException;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:63342/")
public class UserController {

    @Autowired
    UserService userService;

    //check username already in the database
    @GetMapping(value = "/check-username")
    public Mono<ResponseEntity<RespondDTO>> checkUsername(
            @RequestParam("username")
            @NonNull String username){
        //regex checking
        if(username.matches("^[a-zA-Z0-9_.-]{5,30}$")){
            return Mono.just(
                    userService.IsUsernameAlreadyHave(username)
            );
        } else {
            throw new InvalidDataEntryException("Username is invalid !");
        }
    }


    // save new user

    @PostMapping(value = "/signup-user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // fetch data from front end data type
    public Mono<ResponseEntity<RespondDTO>> saveNewUser (
            @RequestPart("signup_name") @NonNull String name,
            @RequestPart("signup_address") @NonNull String address,
            @RequestPart("signup_nic_or_passport") @NonNull String nic_Passport,
            @RequestPart("signup_email") @NonNull String email,
            @RequestPart("signup_username") @NonNull String username,
            @RequestPart("signup_password") @NonNull String password,
            @RequestPart("signupprofile_image") byte[] image){

        if(name.matches("^[a-zA-Z0-9_.-]{4,30}$")){
            if(address.matches("^\\S+\\s*[a-zA-Z0-9,.-]+\\S{0,48}$")){
                if(nic_Passport.matches("^[a-zA-Z0-9_-]+\\S{8,11}$")){
                    if(email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
                        if(username.matches("^[a-zA-Z0-9_.-]{5,30}$")){
                            if (password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")){
                                if(image != null){

                                    return Mono.just(
                                            userService.saveNewUser(
                                                    UserSignupDTO.builder()
                                                            .signup_name(name)
                                                            .signup_address(address)
                                                            .signup_nic_or_passport(nic_Passport)
                                                            .signup_email(email)
                                                            .signup_username(username)
                                                            .signup_password(password)
                                                            .signupprofile_image(image)
                                                            .build()
                                            )
                                    );
                                } else {
                                    throw new InvalidDataEntryException("invalid image");
                                }
                            } else {
                                throw new InvalidDataEntryException("invalid password");
                            }
                        } else {
                            throw new InvalidDataEntryException("invalid username");
                        }
                    } else {
                        throw new InvalidDataEntryException("invalid email");
                    }
                } else {
                    throw new InvalidDataEntryException("invalid nic or passport number");
                }
            } else {
                throw new InvalidDataEntryException("invalid address");
            }
        } else {
            throw new InvalidDataEntryException("invalid name");
        }
    }
}
