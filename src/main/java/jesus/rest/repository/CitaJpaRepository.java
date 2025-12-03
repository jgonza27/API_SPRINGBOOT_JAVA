package jesus.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jesus.rest.model.Cita;

public interface CitaJpaRepository extends JpaRepository<Cita, Long> {
}