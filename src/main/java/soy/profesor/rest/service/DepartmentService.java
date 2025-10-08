package soy.profesor.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soy.profesor.rest.error.DepartmentNotFoundException;
import soy.profesor.rest.model.Department;
import soy.profesor.rest.repository.DepartmentJpaRepository;
import soy.profesor.rest.repository.DepartmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    // Atributos
    private final DepartmentJpaRepository departmentJpaRepository;

    // Métodos
    public List<Department> getAll() {
        var result = departmentJpaRepository.findAll();
        if (result.isEmpty()) throw new RuntimeException();
        return result;
    }



public Department getById(Long id){
    return departmentJpaRepository.findById(id).orElseThrow(  ()-> new DepartmentNotFoundException(id));
}

}