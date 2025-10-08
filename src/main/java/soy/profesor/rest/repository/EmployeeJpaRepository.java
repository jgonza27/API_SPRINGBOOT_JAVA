package soy.profesor.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import soy.profesor.rest.model.Employee;
import java.util.Optional;

public interface EmployeeJpaRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByDni(String DNI);

    Optional<Employee> findByName(String name);

    @Transactional
    void deleteByDni(String DNI);

    Optional<Employee> findFirstByUsername(String username);
}
