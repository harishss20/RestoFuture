package com.example.server.config;

import com.example.server.security.CustomOAuth2SuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;

    public SecurityConfig(CustomOAuth2SuccessHandler customOAuth2SuccessHandler) {
        this.customOAuth2SuccessHandler = customOAuth2SuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http

                .authorizeHttpRequests(auth-> auth
                        .requestMatchers("/","/login/**","/api/login").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2-> oauth2
                        .successHandler(customOAuth2SuccessHandler)
                ).build();
    }
}
