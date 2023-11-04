package lk.nexttravel.apigateway.dto.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:10
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class UserReqNewClientSaveDTO {
    @Id
    private String id;
    private String name_with_initial;
    private String address;
    private String profile_image;
    private String nic_or_passport;

    //---for security purpose
    private String token;
}
