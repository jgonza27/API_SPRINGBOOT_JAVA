package soy.profesor.rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import soy.profesor.rest.repository.EmployeeJpaRepository;

@Service
@RequiredArgsConstructor
public class EmployeeUserDetailsService implements UserDetailsService {

    private final EmployeeJpaRepository employeeJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeJpaRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));
    }
}
