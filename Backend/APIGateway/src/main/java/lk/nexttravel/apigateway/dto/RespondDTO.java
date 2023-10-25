package lk.nexttravel.apigateway.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 11:17
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class RespondDTO {
    private String res_code;
    private String res_mg;
    private Object token;
    private Object data;

}
