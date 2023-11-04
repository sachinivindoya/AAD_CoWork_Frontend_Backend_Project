package lk.nexttravel.apigateway.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:58
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class RespondDTO {
    private String rspd_code;
    private String repd_msg;
    private Object token;
    private Object data;
}
