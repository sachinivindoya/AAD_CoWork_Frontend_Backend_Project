package lk.nexttravel.apigateway.service.serviceImpl;

import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.service.UserService;
import org.springframework.http.HttpStatus;
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
        RespondDTO respondDTO = new RespondDTO();
        System.out.println("method invoked");
        return new ResponseEntity<>(
                respondDTO
                ,
                HttpStatus.ACCEPTED

        );


    }

    @Override
    public ResponseEntity<RespondDTO> userLogin(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        RespondDTO respondDTO = new RespondDTO();
     return new ResponseEntity<>(
             respondDTO
             ,
             HttpStatus.ACCEPTED
     );

    }
}
