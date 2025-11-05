package com.site.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 4. Deshabilitamos CSRF (Cross-Site Request Forgery)
                // No lo necesitamos para una API REST stateless
                .csrf(csrf -> csrf.disable())

                // 5. ¡AQUÍ ESTÁ LA SOLUCIÓN! Deshabilitamos el login por formulario
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable()) // También deshabilitamos el login básico

                // 6. Configuramos las sesiones como STATELESS
                // Le decimos a Spring que no cree sesiones, usaremos JWT
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 7. Definimos las REGLAS DE ACCESO (la parte más importante)
                .authorizeHttpRequests(authz -> authz

                        // 7a. Permite TODAS las peticiones GET a la API del blog
                        .requestMatchers("/api/docs/**").permitAll()

                        // 7b. Permite TODAS las peticiones a la API de autenticación
                        // (Necesitamos que /api/auth/login sea público)
                        .requestMatchers("/api/auth/**").permitAll()

                        // 7c. Para CUALQUIER OTRA petición...
                        .anyRequest().authenticated() // ...requiere autenticación
                );

        return http.build();
    }
}
