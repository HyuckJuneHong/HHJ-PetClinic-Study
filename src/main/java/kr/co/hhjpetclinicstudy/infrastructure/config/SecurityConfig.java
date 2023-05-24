package kr.co.hhjpetclinicstudy.infrastructure.config;

import kr.co.hhjpetclinicstudy.service.model.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

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
                .requestMatchers("/api/v1/admins/login", "/api/v1/admins/denied").permitAll()
                .requestMatchers("/api/v1/admins/**").hasRole(UserRole.ADMIN_ROLE.getUserRole())
                .anyRequest().authenticated();

        //인증 정책
        http
                .formLogin()
                .successHandler(successHandler());

        //로그아웃
        http
                .logout()
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("remember-me");

        //예외 처리
        http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler());

        return http.build();
    }

    private AuthenticationSuccessHandler successHandler() {

        return (request, response, authentication) -> {
            RequestCache requestCache = new HttpSessionRequestCache();
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            String redirectUrl = savedRequest.getRedirectUrl();
            response.sendRedirect(redirectUrl);
        };
    }

    private AuthenticationEntryPoint authenticationEntryPoint() {

        return (request, response, authException) -> {
            response.sendRedirect("/api/v1/admins/login");
        };
    }

    private AccessDeniedHandler accessDeniedHandler() {

        return (request, response, accessDeniedException) -> {
            response.sendRedirect("/api/v1/admins/denied");
        };
    }

    private LogoutSuccessHandler logoutSuccessHandler() {

        return (request, response, authentication) -> {
            response.sendRedirect("/api/v1/admins/login");
        };
    }
}
