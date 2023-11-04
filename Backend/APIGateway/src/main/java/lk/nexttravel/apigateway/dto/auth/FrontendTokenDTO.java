package lk.nexttravel.apigateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class FrontendTokenDTO {
    private String access_username;
    private String access_jwt_token;
    private String access_refresh_token;
}
