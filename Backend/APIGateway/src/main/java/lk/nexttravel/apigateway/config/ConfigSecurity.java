package lk.nexttravel.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.client.RestTemplate;

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 25/10/2023
 * Time    : 19:32
 */
@Configuration
@EnableWebFluxSecurity
public class ConfigSecurity {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder encoder){

        UserDetails users = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();
        return  new MapReactiveUserDetailsService((users));
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain (ServerHttpSecurity httpSecurity){ //This method defines the SecurityWebFilterChain bean and takes a ServerHttpSecurity object as a parameter. The ServerHttpSecurity object is used to configure security for your application.
        return httpSecurity
                .authorizeExchange(auth -> {
          //          auth.pathMatchers("/http://localhost:63342/**").permitAll();  //mekata danone client url eka
                    auth.anyExchange().permitAll(); //okotm permit krl thinne error nisa
                })
                .httpBasic(httpBasic -> httpBasic.disable()) // disable basic http authentication
                .formLogin(formLogin -> formLogin.disable())
                .csrf(csrf -> csrf.disable()) //  This line disables Cross-Site Request Forgery (CSRF) protection. CSRF is a security feature that helps protect against certain types of attacks. By disabling it, you are allowing requests without CSRF tokens.
                .build();

    }
}
