package com.example.Heart_Rate_Monitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF (for testing, enable in production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll() // Allow all API endpoints without authentication
                )
                .formLogin(form -> form.disable()) // Disable default login form
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable basic authentication

        return http.build();
    }

}
