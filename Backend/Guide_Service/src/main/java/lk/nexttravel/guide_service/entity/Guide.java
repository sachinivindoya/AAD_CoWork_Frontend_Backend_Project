package lk.nexttravel.guide_service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "Guide")
public class Guide {
    @Transient
    public static final String SEQUENCE_NAME = "guides_sequence";

    @Id
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

    //for 2PC transaction
    private String transaction_state;
}
