package lk.nexttravel.apigateway.dto.user;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:09
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class ReqUpdateGuideAdminDTO {
    private String id;
    private String name_with_initial;
    private String nic_or_passport;
    private String address;
    private String profile_image;
    private String transaction_state;

    //---for security purpose
    private String token;
}
