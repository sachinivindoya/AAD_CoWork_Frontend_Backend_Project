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
public class UserSignupDTO {
    private String signup_name;
    private String signup_name_with_initial;
    private String signup_email;
    private String signup_password;
    private String signup_nic_or_passport;
    private String signup_address;
    private String signup_profile_image;
}
