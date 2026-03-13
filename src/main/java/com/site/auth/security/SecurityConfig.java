package com.site.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Configuración de CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 4. Deshabilitamos CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())

                // 5. Deshabilitamos el login por formulario y básico
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())

                // 6. Configuramos las sesiones como STATELESS
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 7. Definimos las REGLAS DE ACCESO
                .authorizeHttpRequests(authz -> authz
                        // Permitir preflight requests (OPTIONS) explícitamente por si acaso
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // 7a. Permite TODAS las peticiones GET a la API del blog
                        .requestMatchers(HttpMethod.GET, "/api/blog/**").permitAll()

                        // 7b. Permite TODAS las peticiones GET a la API de experiencia
                        .requestMatchers(HttpMethod.GET, "/api/experience/**").permitAll()

                        // 7c. Permite TODAS las peticiones a la API de autenticación
                        .requestMatchers("/api/auth/**").permitAll()

                        // 7d. Para CUALQUIER OTRA petición...
                        .anyRequest().authenticated()
                );

        // Add JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // IMPORTANTE: Asegúrate de que tu frontend corre en uno de estos puertos
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:4200", "http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
