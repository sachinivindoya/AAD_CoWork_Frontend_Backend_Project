package lk.nexttravel.apigateway.service;

import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.util.RoleTypes;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:27
 */
public interface SystemUserService {

    ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username);

    ResponseEntity<RespondDTO> checkUsernameAndSendOTP(String username);

    ResponseEntity<RespondDTO> verifyUsernameWithOTP(String username, String otp);

    ResponseEntity<RespondDTO> userLoginWithRecoverdPassword(String username, String otp, String password);

    ResponseEntity<RespondDTO> saveNewGuestUser(UserSignupDTO userSignupDTO);

    Mono<ResponseEntity<RespondDTO>> checkUsernamePasswordUserLogin(String username, String password);

    void saveNewAdminUserOnlyTesting(UserSignupDTO userSignupDTO, RoleTypes roleTypes);

    Mono<ResponseEntity<RespondDTO>> userGetProfileData(String access_username, String access_jwt_token, String access_refresh_token);


//    Mono<ResponseEntity<RespondDTO>> getUserProfileImage(String username);
}
