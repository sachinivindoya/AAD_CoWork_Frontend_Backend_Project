package lk.nexttravel.user_service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:15
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
