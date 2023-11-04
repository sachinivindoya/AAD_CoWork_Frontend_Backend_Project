package lk.nexttravel.apigateway.entity.util;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 08:14
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Document(collection = "database_sequences")
@Component
public class DatabaseSequence {
    @Id
    private String id;

    private long seq;
}
