package lk.nexttravel.apigateway.entity;

import lk.nexttravel.apigateway.util.RoleTypes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.beans.Transient;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collation = "User")
public class User {

    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private int mail_otp;

    private RoleTypes roleTypes;



}
