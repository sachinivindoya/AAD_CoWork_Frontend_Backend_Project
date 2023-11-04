package lk.nexttravel.guide_service.entity.util;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:42
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
