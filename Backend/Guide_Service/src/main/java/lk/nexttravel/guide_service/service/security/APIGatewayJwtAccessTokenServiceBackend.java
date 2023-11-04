package lk.nexttravel.guide_service.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:54
 */
@Component
public class APIGatewayJwtAccessTokenServiceBackend {
    public final String JWT_TOKEN_KEY = SecurityCodes.BACKEND_APIGATEWAY_JWT_TOKEN_KEY;

    public final String JWT_TOKEN_USERNAME = SecurityCodes.BACKEND_APIGATEWAY_JWT_TOKEN_USERNAME;

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token) {
        final String userName = extractUserName(token);
        System.out.println((userName.equals(JWT_TOKEN_USERNAME)) && !isTokenExpired(token));
        return (userName.equals(JWT_TOKEN_USERNAME)) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_TOKEN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
