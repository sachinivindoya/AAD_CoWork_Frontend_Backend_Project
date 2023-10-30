package lk.nexttravel.apigateway.config;

import lk.nexttravel.apigateway.util.RoleTypes;
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

/**
 * @author : R.M.Sachini Vinodya
 * Date    : 31/10/2023
 * Time    : 00:28
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder encoder){

        UserDetails users = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();
        return  new MapReactiveUserDetailsService((users));
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity https){
        return https
                .authorizeExchange(auth -> {
//                    auth.pathMatchers("/http://localhost:63342/**").permitAll();  //mekata danone client url eka
                    auth.anyExchange().permitAll(); //okotm permit krl thinne error nisa
                })
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable())
                .csrf(csrf -> csrf.disable())
                .build();
    }


}
