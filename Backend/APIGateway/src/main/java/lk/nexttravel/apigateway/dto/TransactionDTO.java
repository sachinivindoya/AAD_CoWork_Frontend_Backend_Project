package lk.nexttravel.apigateway.dto;

import lombok.*;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class TransactionDTO {
    private String url;
    private HttpMethod httpMethod;
    private Object data;
}
