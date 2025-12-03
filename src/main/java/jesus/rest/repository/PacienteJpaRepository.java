package jesus.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jesus.rest.model.Paciente;

public interface PacienteJpaRepository extends JpaRepository<Paciente, Long> {
    Paciente findByNombre(String nombre);
}