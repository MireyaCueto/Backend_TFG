package com.example.monumentos_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // TODO retirar cuando se suba a prod
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") // Solo administradores
                        .requestMatchers("/api/v1/public/**").permitAll() // Acceso libre
                        .anyRequest().authenticated()) // El resto necesita login
                .httpBasic(Customizer.withDefaults()); // Autenticación básica para pruebas rápidas

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Ejemplo de usuario en memoria (en prod se usará la BD)
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
