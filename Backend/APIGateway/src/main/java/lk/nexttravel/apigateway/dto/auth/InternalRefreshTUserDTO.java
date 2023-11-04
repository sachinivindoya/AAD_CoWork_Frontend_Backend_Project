package lk.nexttravel.apigateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class InternalRefreshTUserDTO {
    private String RefreshToken;
    private boolean isUserAuthenticated;
}
