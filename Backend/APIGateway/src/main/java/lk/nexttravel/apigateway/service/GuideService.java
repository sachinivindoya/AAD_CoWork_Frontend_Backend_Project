package lk.nexttravel.apigateway.service;

import lk.nexttravel.apigateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:25
 */
public interface GuideService {
    //get user admin profile image
    Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token);

    //update
    Mono<ResponseEntity<RespondDTO>> UserAdminUpdateProfileData(String id, String username, String address, String email, String nic, String password, String nameinitial, String profileImage_base64String, String access_username, String access_jwt_token, String access_refresh_token);

    Mono<ResponseEntity<RespondDTO>> createNewGuide(String name, String address, String remarks, String experience, String nic, String nicFrontView, String nicRearView, String tell, String gender, String dob, String image, String perdayFee, String accessUsername, String accessToken, String refreshToken);

    Mono<ResponseEntity<RespondDTO>> getAllGuides(String access_username, String access_jwt_token, String access_refresh_token);

    Mono<ResponseEntity<RespondDTO>> updateGuide(String id, String name, String address, String remarks, String experience, String nic, String nicFrontView, String nicRearView, String tell, String gender, String dob, String image, String perdayFee, String accessUsername, String accessToken, String refreshToken);

    Mono<ResponseEntity<RespondDTO>> deleteGuide(String id, String access_username, String access_jwt_token, String access_refresh_token);
}
