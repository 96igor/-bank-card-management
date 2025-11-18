package com.igor.bankcardmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Отключаем CSRF для REST API
            .csrf(AbstractHttpConfigurer::disable)
            
            // Настраиваем CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            
            // Настраиваем авторизацию запросов
            .authorizeHttpRequests(auth -> auth
                // Разрешаем доступ к Swagger UI и документации API
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/**"
                ).permitAll()
                
                // Разрешаем доступ к публичным эндпоинтам (можно добавить /api/auth/** для аутентификации)
                .requestMatchers("/api/auth/**").permitAll()
                
                // ВРЕМЕННО: разрешаем доступ ко всем API эндпоинтам для разработки
                // TODO: После реализации JWT аутентификации изменить на .authenticated()
                .requestMatchers("/api/**").permitAll()
                
                // Все остальные запросы требуют аутентификации
                .anyRequest().authenticated()
            )
            
            // Настраиваем сессии как stateless (для JWT)
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Разрешаем запросы с этих источников (можно настроить под ваши нужды)
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
        
        // Разрешаем эти HTTP методы
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        
        // Разрешаем эти заголовки
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // Разрешаем отправку credentials (cookies, авторизация)
        configuration.setAllowCredentials(true);
        
        // Кэшируем конфигурацию CORS
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}


