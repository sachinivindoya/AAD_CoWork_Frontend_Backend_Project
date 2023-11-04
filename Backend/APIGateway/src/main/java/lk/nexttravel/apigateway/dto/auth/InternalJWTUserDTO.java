package lk.nexttravel.apigateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class InternalJWTUserDTO {
    private String Username;
    private RoleTypes Role;
    private String AccessToken;
    private boolean isUserAuthorized;
}
