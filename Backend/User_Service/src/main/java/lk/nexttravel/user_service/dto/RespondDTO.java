package lk.nexttravel.user_service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 30/10/2023
 * Time    : 17:21
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
