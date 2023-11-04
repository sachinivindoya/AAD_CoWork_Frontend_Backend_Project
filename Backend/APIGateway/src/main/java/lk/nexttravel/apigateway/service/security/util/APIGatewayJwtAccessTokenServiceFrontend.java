package lk.nexttravel.apigateway.service.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lk.nexttravel.apigateway.dto.auth.InternalJWTUserDTO;
import lk.nexttravel.apigateway.entity.User;
import lk.nexttravel.apigateway.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.*;
import java.util.function.Function;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:56
 */
@Component
public class APIGatewayJwtAccessTokenServiceFrontend {
    @Autowired
    UserRepository userRepository;

    public final long JWT_TOKEN_VALIDITY = SecurityCodes.FRONTEND_APIGATEWAY_JWT_TOKEN_KEY_VALIDITY;

    public final String JWT_TOKEN_KEY = SecurityCodes.FRONTEND_APIGATEWAY_JWT_TOKEN_KEY;

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_TOKEN_KEY).parseClaimsJws(token).getBody();
    }

    //generate token for user
    public String generateToken(String Username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, Username);
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_TOKEN_KEY).compact();
    }

    //validateUpdateGetUserJWT  - this method check JWT and if it expired create new and role and user name return
    public InternalJWTUserDTO validateUpdateGetUserJWT(String token, String username){
        System.out.println(username+"t   t");
        System.out.println(token);

        InternalJWTUserDTO internalJWTUserDTO = new InternalJWTUserDTO();
        Optional<RoleTypes> userRoletype = getRoleByUsername(username); //username with has a role type  and pick it
        System.out.println(userRoletype.isPresent());

        //check JWT
        try {
            if(userRoletype.isPresent()){ //check database and get role
                internalJWTUserDTO.setRole(userRoletype.get());//gett role from user database

                Jwts.parser()
                        .setSigningKey( new SecretKeySpec(Base64.getDecoder().decode(JWT_TOKEN_KEY), SignatureAlgorithm.HS512.getJcaName()))
                        .parseClaimsJws(token);

                //add data
                internalJWTUserDTO.setAccessToken(token);
                internalJWTUserDTO.setUsername(username);
                internalJWTUserDTO.setUserAuthorized(true);
                return internalJWTUserDTO;

            }else{
                internalJWTUserDTO.setUserAuthorized(false);
                return internalJWTUserDTO;
            }

        } catch (ExpiredJwtException e) { //if token expired
            System.out.println(e.getMessage());
            internalJWTUserDTO.setUserAuthorized(true);
            internalJWTUserDTO.setAccessToken(generateToken(username)); //generate and add new token
            internalJWTUserDTO.setUsername(username);
            return internalJWTUserDTO;

        } catch (Exception e) { //if token mismatched
            System.out.println(e.getMessage());
            internalJWTUserDTO.setUserAuthorized(false);
            internalJWTUserDTO.setRole(null);
            return internalJWTUserDTO;
        }

    }

    public Optional<RoleTypes> getRoleByUsername(String username) {
        Optional<User> user = userRepository.findUserByName(username);
        if (user.isPresent()) {
            return Optional.of(user.get().getRole_type());
        } else {
            return Optional.empty();
        }
    }
}
