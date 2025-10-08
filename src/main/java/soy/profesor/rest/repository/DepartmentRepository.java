package soy.profesor.rest.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import soy.profesor.rest.model.Department;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@Repository
public class DepartmentRepository {

    // Atributos
    private HashMap<Long, Department> departments = new HashMap<>();

    // Métodos
    @PostConstruct
    public void init() {
        var d = Department.builder()
                .id(1L)
                .name("Informática")
                .phone("950 15 61 51")
                .email("informatica@iescelia.org")
                .build();

        departments.put(d.getId(), d);
    }

    // Obtener todos los departamentos
    public List<Department> getAll() {
        return List.copyOf(departments.values());
    }

    // Añadir un departamento
    public Department add(Department department) {
        departments.put(department.getId(), department);
        return department;
    }

    // Obtener un departamento por ID
    public Optional<Department> getById(Long id) {
        return Optional.ofNullable(departments.get(id));
    }

    // Eliminar un departamento por ID
    public boolean delete(Long id) {
        return departments.remove(id) != null; // Devuelve true si fue eliminado, false si no fue encontrado
    }
    public Optional<Department> edit(Long id, Department newValue) {
        return Optional.ofNullable(departments.computeIfPresent(id, (Long k, Department v) -> {
            v.setName(newValue.getName());
            v.setPhone(newValue.getPhone());
            v.setEmail(newValue.getEmail());
            return v;
        }));
    }

}
