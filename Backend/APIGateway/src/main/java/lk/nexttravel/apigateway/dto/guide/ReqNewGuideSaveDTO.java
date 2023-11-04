package lk.nexttravel.apigateway.dto.guide;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:05
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class ReqNewGuideSaveDTO {
    private String id;

    private String name;

    private String remarks;

    private int experience;

    private String nic;

    private String nic_front_view;

    private String nic_rear_view;

    private String tell;

    private String gender;

    private String dob;

    private String image;

    private String address;

    private int perday_fee;

    //---for security purpose
    private String token;
}
