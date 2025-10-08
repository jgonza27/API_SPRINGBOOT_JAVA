package soy.profesor.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        // Devuelve un codificador delegante que usa BCrypt por defecto
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
