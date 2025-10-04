package com.example.Notifi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        // ✅ Disable CSRF only for notifications API
                        .ignoringRequestMatchers("/notifications/**")
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/notifications/**").permitAll() // allow notifications without login
                        .anyRequest().authenticated() // everything else requires login
                )
                .httpBasic(Customizer.withDefaults()); // keep basic auth enabled

        return http.build();
    }
}
