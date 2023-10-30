package lk.nexttravel.apigateway.entity.util;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 17:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Document(collection = "database_sequences")
@Component
public class DBSequence {
    @Id
    private String id;

    private long seq;
}
