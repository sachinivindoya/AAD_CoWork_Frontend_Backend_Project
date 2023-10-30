package lk.nexttravel.apigateway.service.securityService.util;

import lk.nexttravel.apigateway.dto.auth.InternalUserRefreshTokenDTO;
import lk.nexttravel.apigateway.entity.RefreshToken;
import lk.nexttravel.apigateway.entity.User;
import lk.nexttravel.apigateway.repostory.RefreshTokenRepository;
import lk.nexttravel.apigateway.repostory.UserRepository;
import lk.nexttravel.apigateway.util.securityCodes.SecurityCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


/**
 * @author : R.M.Sachini Vinodya
 * Date    : 31/10/2023
 * Time    : 01:03
 */
@Service
public class RefreshTokenServiceFrontend {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    InternalUserRefreshTokenDTO internalUserRefreshTokenDTO;

    public String createRefreshToken(User user) {
        String token = UUID.randomUUID().toString();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(user.getId())
                        .token(token)
                        .expiredate(Instant.now().plusMillis(SecurityCodes.FRONTEND_APIGATEWAY_REFRESH_TOKEN_KEY_VALIDITY))
                        .build()
        );
        return token;
    }




//    public InternalUserRefreshTokenDTO validateUpdateGetUserJWT(String refreshtoken, String username){
//        Optional<User> user = userRepository.findUserByName(username);
//        Optional<RefreshToken> DBtoken = refreshTokenRepository.findRefreshTokenById(user.get().getId());
//        //check this token saved on DB
//        if(DBtoken.isPresent()){
//            //check DBtoken and recieved token matched
//            if(DBtoken.get().getToken().equals(refreshtoken)){
//                //check it expired
//                if(!isExpired(DBtoken.get())){
//                    internalUserRefreshTokenDTO.setRefreshToken(refreshtoken);
//                    internalUserRefreshTokenDTO.setUserAuthenticated(true);
//                    return internalUserRefreshTokenDTO;
//                }else{
//                    //if expired
//                    //delte it
//                    refreshTokenRepository.delete(DBtoken.get());
//                    //generate new one
//                    String tokenkey = createRefreshToken(userRepository.findUserByName(username).get());
//                    internalUserRefreshTokenDTO.setRefreshToken(tokenkey);
//                    internalUserRefreshTokenDTO.setUserAuthenticated(true);
//                    return internalUserRefreshTokenDTO;
//                }
//            }else{
//                internalUserRefreshTokenDTO.setUserAuthenticated(false);
//                return internalUserRefreshTokenDTO;
//            }
//        }else{
//            internalUserRefreshTokenDTO.setUserAuthenticated(false);
//            return internalUserRefreshTokenDTO;
//        }
//    }


    public boolean isExpired(RefreshToken token) {
        if (token.getExpiredate().compareTo(Instant.now()) < 0) {
            return true;
        } else {
            return false;
        }
    }
}
