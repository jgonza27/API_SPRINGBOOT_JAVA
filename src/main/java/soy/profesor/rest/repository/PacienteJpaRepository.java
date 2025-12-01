package soy.profesor.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soy.profesor.rest.model.Paciente;

public interface PacienteJpaRepository extends JpaRepository<Paciente, Long> {
    Paciente findByNombre(String nombre);
}