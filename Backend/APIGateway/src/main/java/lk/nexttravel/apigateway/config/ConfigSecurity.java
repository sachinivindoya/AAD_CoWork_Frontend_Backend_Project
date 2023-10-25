package lk.nexttravel.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 19:32
 */
@Configuration
public class ConfigSecurity {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
