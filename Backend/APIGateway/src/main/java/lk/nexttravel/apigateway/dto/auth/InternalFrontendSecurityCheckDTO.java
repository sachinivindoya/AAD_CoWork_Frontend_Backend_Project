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
public class InternalFrontendSecurityCheckDTO {
    private String username;
    private String access_token;
    private String refresh_token;
    private boolean accesssible;
    private RoleTypes role;
}
