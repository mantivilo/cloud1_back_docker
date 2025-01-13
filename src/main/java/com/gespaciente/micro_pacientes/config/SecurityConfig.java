package com.gespaciente.micro_pacientes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/pacientes").permitAll() // Deja público /api/pacientes
                .anyRequest().authenticated() // Todo lo demás requiere autenticación
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        //return NimbusJwtDecoder.withJwkSetUri("https://login.microsoftonline.com/145f8758-681d-4413-9137-2973073f86e2/discovery/v2.0/keys").build();
        return NimbusJwtDecoder.withJwkSetUri("https://login.microsoftonline.com/da33d9d5-d2db-47f9-9d73-996ec0dd60d2/discovery/v2.0/keys").build();
    }

    //https://login.microsoftonline.com/da33d9d5-d2db-47f9-9d73-996ec0dd60d2/v2.0
}
