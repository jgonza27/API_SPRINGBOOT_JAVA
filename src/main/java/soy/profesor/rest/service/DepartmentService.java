package soy.profesor.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soy.profesor.rest.dto.NewDepartmentDTO;
import soy.profesor.rest.dto.NewEmployeeDTO;
import soy.profesor.rest.error.DepartmentNotFoundException;
import soy.profesor.rest.model.Department;
import soy.profesor.rest.repository.DepartmentJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    // Atributos
    private final DepartmentJpaRepository departmentJpaRepository;

    // Métodos

    // Obtener todos los departamentos
    public List<Department> getAll() {
        var result = departmentJpaRepository.findAll();
        if (result.isEmpty()) {
            throw new DepartmentNotFoundException();
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay departamentos
            // con esos requisitos de búsqueda");
        }
        return result;
    }

    // Obtener un departamento por ID
    public Department getById(Long id) {
        return departmentJpaRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    // Crear un nuevo departamento
    public Optional<Department> create(NewDepartmentDTO dto) {
        Department d = new Department();
        d.setName(dto.name());
        d.setPhone(dto.phone());
        d.setEmail(dto.email());
        return Optional.of(departmentJpaRepository.save(d));
    }

    // Editar un departamento existente
    public Department edit(Long id, NewDepartmentDTO dto) {
        return departmentJpaRepository.findById(id)
                .map(dept -> {
                    dept.setName(dto.name());
                    dept.setPhone(dto.phone());
                    dept.setEmail(dto.email());
                    return departmentJpaRepository.save(dept);
                })
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    // Eliminar un departamento por ID
    public Optional<Department> delete(Long id) {
        var result = departmentJpaRepository.findById(id);
        if (result.isEmpty()) {
            throw new DepartmentNotFoundException(id);
        }
        departmentJpaRepository.deleteById(id);
        return result;
    }

    public List<NewEmployeeDTO> getEmployeesById(Long id) {
        var result = departmentJpaRepository.findEmployeesByDepartmentId(id);
        if (result.isEmpty())
            throw new DepartmentNotFoundException(id);
        return result;
    }

}
