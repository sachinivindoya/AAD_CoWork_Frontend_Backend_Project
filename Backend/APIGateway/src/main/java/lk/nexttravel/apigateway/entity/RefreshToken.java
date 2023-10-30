package lk.nexttravel.apigateway.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 31/10/2023
 * Time    : 00:48
 */


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Document(collection = "RefreshToken")
public class RefreshToken {

    @Id
    private String id;

    private String token;

    private Instant expiredate;
}
