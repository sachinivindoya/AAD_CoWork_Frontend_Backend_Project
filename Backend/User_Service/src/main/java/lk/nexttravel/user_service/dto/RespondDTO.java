package lk.nexttravel.user_service.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 03/11/2023
 * Time    : 22:21
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
