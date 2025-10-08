package soy.profesor.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soy.profesor.rest.model.Department;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import soy.profesor.rest.dto.NewEmployeeDTO;

public interface DepartmentJpaRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);

    @Query("SELECT new soy.profesor.rest.dto.NewEmployeeDTO(e.dni, e.name, e.username, e.password, e.department.id) FROM Employee e JOIN Department d ON e.department.id = d.id WHERE d.id = :id")
    List<NewEmployeeDTO> findEmployeesByDepartmentId(@Param("id") Long id);
}
