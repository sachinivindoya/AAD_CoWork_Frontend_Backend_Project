package lk.nexttravel.apigateway.util.builders;

import lk.nexttravel.apigateway.dto.mail.MailDTO;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.util.Properties;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 10:03
 */

@Component
public class MailBuilder {

    private String subject;
    private String mailTo;
    private String mailFrom;
    private String template;
    private final VelocityContext velocityContext;
    private final VelocityEngine velocityEngine;

    public MailBuilder() {
        this.mailTo = "";
        this.mailFrom = "";
        this.subject = "";
        this.template = "";
        this.velocityContext = new VelocityContext();
        final Properties properties = new Properties();
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("output.encoding", "UTF-8");
        properties.setProperty("resource.loader", "file, class, jar");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        this.velocityEngine = new VelocityEngine(properties);
    }

    public MailBuilder Subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailBuilder To(String to) {
        this.mailTo = to;
        return this;
    }

    public MailBuilder From(String from) {
        this.mailFrom = from;
        return this;
    }

    public MailBuilder Template(String template) {
        this.template = template;
        return this;
    }

    public MailBuilder AddContext(String key, String value) {
        velocityContext.put(key, value);
        return this;
    }

    public MailBuilder AddContext(String key, Object value) {
        velocityContext.put(key, value);
        return this;
    }

    public MailDTO createMail() throws IllegalArgumentException {
        final Template templateEngine = velocityEngine.getTemplate("templates/" + this.template);
        final StringWriter stringWriter = new StringWriter();
        templateEngine.merge(this.velocityContext, stringWriter);
        if(this.mailTo.isEmpty() || this.mailFrom.isEmpty()) {
            throw new IllegalArgumentException("Missing mail headers");
        }
        final MailDTO result = new MailDTO();
        result.setMailTo(this.mailTo);
        result.setMailFrom(this.mailFrom);
        result.setMailContent(stringWriter.toString());
        result.setMailSubject(this.subject);

        return result;
    }
}
