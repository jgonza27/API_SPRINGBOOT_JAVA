package soy.profesor.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import soy.profesor.rest.dto.LoginUserDTO;
import soy.profesor.rest.dto.RegisterUserDTO;
import soy.profesor.rest.model.User;
import soy.profesor.rest.repository.UserJpaRepository;
import soy.profesor.rest.security.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserJpaRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody LoginUserDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password()));

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtUtils.generateToken(userDetails.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("username", userDetails.getUsername());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterUserDTO registerDTO) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(registerDTO.username()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El usuario ya existe"));
        }

        // Crear nuevo usuario
        User user = User.builder()
                .username(registerDTO.username())
                .password(encoder.encode(registerDTO.password()))
                .role(registerDTO.role())
                .enabled(true)
                .build();

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Usuario registrado exitosamente");
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }
}