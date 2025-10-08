package soy.profesor.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee implements UserDetails {
    @Id
    private String dni;
    private String name;
    // private String login;
    private String username;
    private String password;
    @ManyToOne
    private Department department;

    @Builder.Default
    private boolean isAdmin = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = "ROLE_" + ((isAdmin) ? "ADMIN" : "USER");
        return List.of(new SimpleGrantedAuthority(role));
    }

}
