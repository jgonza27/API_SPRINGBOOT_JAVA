package soy.profesor.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import soy.profesor.rest.model.Sanitario;

import java.util.Optional;

public interface SanitarioJpaRepository extends JpaRepository<Sanitario, Long> {

	Optional<Sanitario> findByName(String name);

	Optional<Sanitario> findByEspecialidad(String especialidad);

	@Transactional
	void deleteByName(String name);
}