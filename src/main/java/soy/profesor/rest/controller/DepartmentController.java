package soy.profesor.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soy.profesor.rest.model.Department;
import soy.profesor.rest.repository.DepartmentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import soy.profesor.rest.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    // Atributos
    private final DepartmentService departmentService;

    // Métodos
    @GetMapping
    public List<Department> index(){
        return departmentService.getAll();
    }
    /*public ResponseEntity<List<Department>> index() {
        List<Department> result = departmentRepository.getAll();
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }*/

    @GetMapping("/{id}")
    public Department show (@PathVariable Long id){
        return departmentService.getById(id);
    }

   /* @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department department) {
        Department createdDepartment = departmentRepository.add(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> show(@PathVariable Long id) {
        return departmentRepository.getById(id)
                .map(department -> ResponseEntity.ok(department)) // Si se encuentra, devuelve el departamento
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no se encuentra, devuelve NOT FOUND
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Elimina el departamento y revisa si fue exitoso
        boolean deleted = departmentRepository.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content si se eliminó
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found si no se encontró el departamento
        }
    }

    // Endpoint PUT /api/department/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Department> edit(
            @RequestBody Department deptoData,
            @PathVariable("id") Long deptoId) {
        return ResponseEntity.of(departmentRepository.edit(deptoId, deptoData));
    }*/

}
