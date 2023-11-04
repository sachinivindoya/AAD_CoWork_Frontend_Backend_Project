package lk.nexttravel.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:52
 */
@Configuration
public class RestTemplateConig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
