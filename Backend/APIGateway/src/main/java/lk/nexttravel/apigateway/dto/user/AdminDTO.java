package lk.nexttravel.apigateway.dto.user;

import lombok.*;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 16:08
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

public class AdminDTO {
    private String id;
    private String signup_name_with_initial;
    private String nic_or_passport;
    private String address;
    private long salary;
    private String profile_image;

    //for 2PC transaction
    private String transaction_state;
}
