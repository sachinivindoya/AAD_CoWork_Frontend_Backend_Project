package lk.nexttravel.apigateway.dto.user;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class ReqProfileDataAdminsDTO {
    private String name_with_initial;

    private String nic_or_passport;

    private String address;

    private String profile_image;
}
