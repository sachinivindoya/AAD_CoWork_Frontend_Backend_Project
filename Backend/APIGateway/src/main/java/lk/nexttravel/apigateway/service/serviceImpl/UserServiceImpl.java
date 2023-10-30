package lk.nexttravel.apigateway.service.serviceImpl;

import lk.nexttravel.apigateway.dto.RespondDTO;
import lk.nexttravel.apigateway.dto.auth.UserSignupDTO;
import lk.nexttravel.apigateway.entity.User;
import lk.nexttravel.apigateway.repostory.UserRepository;
import lk.nexttravel.apigateway.service.SequenceGeneratorService;
import lk.nexttravel.apigateway.service.UserService;
import lk.nexttravel.apigateway.util.ResCodes;
import lk.nexttravel.apigateway.util.RoleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Optional;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 14:27
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public ResponseEntity<RespondDTO> IsUsernameAlreadyHave(String username) {
        return null;
    }

    @Override
    public ResponseEntity<RespondDTO> saveNewUser(UserSignupDTO userSignupDTO) {
        System.out.println("saved");
        String id = "U00"+sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
//        String password = passwordEncoder.encode(userSignupDTO.getSignup_password());
        String encodedImage = Base64.getEncoder().encodeToString(userSignupDTO.getSignupprofile_image());
        Optional<User> savedUser;
        savedUser = Optional.of(
                userRepository.save(
                        User.builder()
                                .id(id)
                                .name(userSignupDTO.getSignup_name())
                                .email(userSignupDTO.getSignup_email())
                                .password(userSignupDTO.getSignup_password())
                                .roleTypes(RoleTypes.CLIENT)

                                .build()
                )

        );
              return new ResponseEntity<RespondDTO> (
                     RespondDTO.builder()
                      .res_code(ResCodes.Response_DATA_SAVED)
//                      .token(frontendTokenDTO)
                      .data(savedUser.get().getRoleTypes())
                      .build()
                        ,
                      HttpStatus.CREATED);

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
