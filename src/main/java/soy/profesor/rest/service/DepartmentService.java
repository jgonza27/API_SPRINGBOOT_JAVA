package soy.profesor.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soy.profesor.rest.dto.NewDepartmentDTO;
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
        return departmentJpaRepository.findAll();
    }

    // Obtener un departamento por ID
    public Department getById(Long id) {
        return departmentJpaRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    // Crear un nuevo departamento
    public Optional<Department> create(NewDepartmentDTO dto) {
        Department d = new Department();
        d.setName(dto.Name());
        d.setPhone(dto.Phone());
        d.setEmail(dto.Email());
        return Optional.of(departmentJpaRepository.save(d));
    }

    // Editar un departamento existente
    public Department edit(Long id, NewDepartmentDTO dto) {
        return departmentJpaRepository.findById(id)
                .map(dept -> {
                    dept.setName(dto.Name());
                    dept.setPhone(dto.Phone());
                    dept.setEmail(dto.Email());
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
}
