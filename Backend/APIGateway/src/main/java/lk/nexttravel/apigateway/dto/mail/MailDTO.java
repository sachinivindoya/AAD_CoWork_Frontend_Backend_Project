package lk.nexttravel.apigateway.dto.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 09:59
 */
@AllArgsConstructor
@Data
@Builder
@ToString

@Component
public class MailDTO {
    private String mailFrom;
    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailContent;
    private String templateName;
    private String contentType;

    public MailDTO() {
        this.contentType = "text/html";
    }
}
