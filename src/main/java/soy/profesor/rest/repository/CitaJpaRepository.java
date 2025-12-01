package soy.profesor.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soy.profesor.rest.model.Cita;

public interface CitaJpaRepository extends JpaRepository<Cita, Long> {
}