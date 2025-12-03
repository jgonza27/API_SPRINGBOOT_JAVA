package jesus.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jesus.rest.model.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}