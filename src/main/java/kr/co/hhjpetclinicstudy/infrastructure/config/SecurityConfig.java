package kr.co.hhjpetclinicstudy.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
                .formLogin()
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .loginProcessingUrl("/login_proc")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
        ;

        return http.build();
    }

    private AuthenticationSuccessHandler authenticationSuccessHandler() {

        return (request, response, authentication) -> {
            System.out.println("Authentication : " + authentication.getName());
            response.sendRedirect("/");
        };
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {

        return (request, response, exception) -> {
            System.out.println("Exception : " + exception.getMessage());
            response.sendRedirect("/login");
        };
    }
}
