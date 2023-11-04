package lk.nexttravel.apigateway.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "User")
public class User {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String id;

    private String name;  //uniq

    private String email;

    private String password;

    private String mail_otp;

    private RoleTypes role_type;

    //for 2PC transaction
    private String transaction_state;
}
