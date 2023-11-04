package lk.nexttravel.apigateway.service;

import lk.nexttravel.apigateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:27
 */
public interface UserService {
    //get user admin profile image
    Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(
            String access_username,
            String access_jwt_token,
            String access_refresh_token);

    //get all admins
    Mono<ResponseEntity<RespondDTO>> getAllAdminsBySearch(
            String search_keyword ,
            String access_username,
            String access_jwt_token,
            String access_refresh_token);

    //request to logout
    Mono<ResponseEntity<RespondDTO>> requestToLogout(
            String access_username,
            String access_jwt_token,
            String access_refresh_token);
}


