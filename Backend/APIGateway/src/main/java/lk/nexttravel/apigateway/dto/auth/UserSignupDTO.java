package lk.nexttravel.apigateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 11:26
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Component
public class UserSignupDTO {
    private String signup_name;
    private String signup_address;
    private String signup_nic_or_passport;
    private String signup_email;
    private String signup_username;
    private String signup_password;
    private byte [] signupprofile_image;
}
