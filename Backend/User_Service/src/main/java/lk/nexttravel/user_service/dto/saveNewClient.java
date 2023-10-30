package lk.nexttravel.user_service.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 17:56
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class saveNewClient {
    @Id
    private String id;
    private String name_with_initial;
    private String address;
    private String nic_or_passport;
    private String profile_image;


    //---for security purpose
    private String token;

}
