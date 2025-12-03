package jesus.rest.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jesus.rest.model.User;
import jesus.rest.repository.UserJpaRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Inicializando usuarios por defecto...");

        // Eliminar usuarios existentes si los hay
        userRepository.deleteAll();

        // Crear usuario admin
        User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password123"))
                .role("ROLE_ADMIN")
                .enabled(true)
                .build();
        userRepository.save(admin);
        log.info("✓ Usuario admin creado - username: admin, password: password123");

        // Crear usuario normal
        User user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password123"))
                .role("ROLE_USER")
                .enabled(true)
                .build();
        userRepository.save(user);
        log.info("✓ Usuario user creado - username: user, password: password123");
    }
}