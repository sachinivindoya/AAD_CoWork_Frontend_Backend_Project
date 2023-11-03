package lk.nexttravel.user_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:25
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Document(collection = "Admin")
public class Admin {
    @Id
    private String id;
    private String signup_name_with_initial;
    private String nic_or_passport;
    private String address;
    private long salary;
    private String profile_image;

    //for 2PC transaction
    private String transaction_state;
}
