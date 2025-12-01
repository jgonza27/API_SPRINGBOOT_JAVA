package soy.profesor.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soy.profesor.rest.model.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}