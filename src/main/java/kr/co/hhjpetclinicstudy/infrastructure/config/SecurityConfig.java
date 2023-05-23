package kr.co.hhjpetclinicstudy.infrastructure.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

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

        //로그아웃
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler(logoutHandler())
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("remember-me");

        return http.build();
    }

    private LogoutHandler logoutHandler() {

        return (request, response, authentication) -> {
            HttpSession session = request.getSession();
            session.invalidate();
        };
    }

    private LogoutSuccessHandler logoutSuccessHandler() {

        return (request, response, authentication) -> {
            response.sendRedirect("/login");
        };
    }
}
