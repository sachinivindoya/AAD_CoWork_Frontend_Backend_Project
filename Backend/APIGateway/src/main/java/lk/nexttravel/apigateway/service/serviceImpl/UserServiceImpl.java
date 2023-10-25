package lk.nexttravel.apigateway.service.serviceImpl;

import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 14:27
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public ResponseEntity<RespondDTO> IsUsernameAlreadyHave(String username) {
        return null;
    }

    @Override
    public ResponseEntity<RespondDTO> saveNewUser(UserSignupDTO userSignupDTO) {
        return null;
    }
}
