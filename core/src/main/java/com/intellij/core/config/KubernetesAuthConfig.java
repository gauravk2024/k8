package com.intellij.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.intellij.core.security.KubernetesAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class KubernetesAuthConfig {

    @Value("${kubernetes.auth.public-key-path}")
    private String publicKeyPath;

    @Value("${kubernetes.auth.token-audience}")
    private String tokenAudience;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(kubernetesAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public KubernetesAuthenticationFilter kubernetesAuthenticationFilter() {
        return new KubernetesAuthenticationFilter(publicKeyPath, tokenAudience);
    }
} 