package lk.nexttravel.apigateway.service;

import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 11:05
 */
public interface UserService {

    ResponseEntity<RespondDTO> IsUsernameAlreadyHave (String username);

    ResponseEntity<RespondDTO> saveNewUser (UserSignupDTO userSignupDTO);

    ResponseEntity<RespondDTO> userLogin (String username, String password);
}
