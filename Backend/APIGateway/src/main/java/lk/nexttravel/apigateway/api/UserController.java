package lk.nexttravel.apigateway.api;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 10:50
 */


import lk.nexttravel.apigateway.advice.util.InvalidDataEntryException;
import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
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
}
