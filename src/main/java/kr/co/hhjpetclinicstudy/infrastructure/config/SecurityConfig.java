package kr.co.hhjpetclinicstudy.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {

        //인가 정책
        http
                .authorizeHttpRequests()
                .anyRequest().authenticated();

        //인증 정책
        http
                .formLogin();

        return http.build();
    }
}
