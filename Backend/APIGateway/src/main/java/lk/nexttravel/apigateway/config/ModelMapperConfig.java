package lk.nexttravel.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 04/11/2023
 * Time    : 07:51
 */
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
