package kr.co.hhjpetclinicstudy.infrastructure.config;

import kr.co.hhjpetclinicstudy.service.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {

        //인가 정책
        http
                .securityMatcher("/api/v1/admins/**")
                .authorizeHttpRequests()
                    .requestMatchers("/api/v1/admins/login").permitAll()
                    .requestMatchers("/api/v1/admins/**").hasRole(UserRole.ADMIN_ROLE.getUserRole())
                .anyRequest().authenticated();

        //인증 정책
        http
                .formLogin();

        //로그아웃
        http
                .logout()
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("remember-me");

        return http.build();
    }

    private LogoutSuccessHandler logoutSuccessHandler() {

        return (request, response, authentication) -> {
            response.sendRedirect("/login");
        };
    }
}
