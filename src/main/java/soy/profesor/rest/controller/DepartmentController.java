package soy.profesor.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soy.profesor.rest.dto.NewDepartmentDTO;
import soy.profesor.rest.dto.NewEmployeeDTO;
import soy.profesor.rest.model.Department;
import soy.profesor.rest.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/private/department")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "[*]")

public class DepartmentController {

    private final DepartmentService departmentService;

    // Endpoint GET /api/department
    @GetMapping
    public List<Department> index() {
        return departmentService.getAll();
    }

    // Endpoint GET /api/department/{id}
    @GetMapping("/{id}")
    public Department show(@PathVariable Long id) {
        return departmentService.getById(id);
    }

    // Endpoint POST /api/department
    @PostMapping
    public ResponseEntity<Department> store(@RequestBody NewDepartmentDTO department) {
        return ResponseEntity.of(departmentService.create(department));
    }

    // Endpoint PUT /api/department/{id}
    @PutMapping("/{id}")
    public Department edit(@RequestBody NewDepartmentDTO deptoData, @PathVariable("id") Long deptoId) {
        return departmentService.edit(deptoId, deptoData);
    }

    // Endpoint DELETE /api/department/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint GET /api/department/employee/{id}
    @GetMapping("/employee/{id}")
    public List<NewEmployeeDTO> showEmployeesOfDepartment(@PathVariable Long id) {
        return departmentService.getEmployeesById(id);
    }

}
