package lk.nexttravel.apigateway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:49
 */
@RestController
@RequestMapping("/user-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class UserServiceController {

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    @Autowired
    UserService userService;

    //user login
    @GetMapping(value = {"/user-admin-get-profile-data"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileImage(
            @RequestParam("access_username") @NonNull String access_username,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return userService.UserAdminGetProfileData(
                access_username,
                access_jwt_token,
                access_refresh_token
        );
    }

    //admin manage window- search all admins
    @GetMapping(value = {"/admin-mng-get-all-admins"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> getAllAdminsDataList(
            @RequestParam("search_keyword") @NonNull String search_keyword,
            @RequestParam("access_username") @NonNull String access_username,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return userService.getAllAdminsBySearch(
                search_keyword,
                access_username,
                access_jwt_token,
                access_refresh_token
        );
    }

    //logout request - Profile User Manage Console
    @GetMapping(value = {"/request-to-logout"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> requestToLogout(
            @RequestParam("access_username") @NonNull String access_username,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return userService.requestToLogout(
                access_username,
                access_jwt_token,
                access_refresh_token
        );
    }
}
