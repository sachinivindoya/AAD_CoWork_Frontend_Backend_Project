package lk.nexttravel.apigateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 31/10/2023
 * Time    : 01:11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class InternalUserRefreshTokenDTO {
    private String RefreshToken;
    private boolean isUserAuthenticated;
}
