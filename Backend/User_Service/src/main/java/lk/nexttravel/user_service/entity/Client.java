package lk.nexttravel.user_service.entity;

import lk.nexttravel.user_service.util.Gender;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 17:59
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Document(collection = "Client")
public class Client {
    @Id
    private String id;
    private String name_with_initial;
    private String address;
    private String profile_image;
    private String nic_or_passport;
    private String nic_or_passport_front_view;
    private String nic_or_passport_rear_view;
    private Gender gender;
    private long Tell;
    private String Remarks;
    private int age;

    //for 2PC transaction
    private String transaction_state;
}
