package com.Rabka.rabka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurtiyConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        HttpSecurity security = http
                .sessionManagement(menagement -> menagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS ))
                .authorizeHttpRequests(Aurthorize -> Aurthorize.requestMatchers("/api/**").authenticated()
                        .requestMatchers("/api/super-admin/**").authenticated()
                                .anyRequest().hasRole("ADMIN")
                                .anyRequest().permitAll()

                        );
        return security.build();


    }
}
