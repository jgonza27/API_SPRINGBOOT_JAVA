package soy.profesor.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soy.profesor.rest.dto.loginEmployeeDTO;
import soy.profesor.rest.repository.EmployeeJpaRepository;
import soy.profesor.rest.security.JwtUtil;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private EmployeeJpaRepository userRepository;
    private PasswordEncoder encoder;
    private JwtUtil jwtUtils;

    @Autowired
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            EmployeeJpaRepository userRepository,
            PasswordEncoder encoder,

            JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody loginEmployeeDTO user) {
        Authentication authentication = authenticationManager.authenticate(
                new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(user.username(),
                        user.password()));
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }
}