package soy.profesor.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soy.profesor.rest.model.Department;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
}
