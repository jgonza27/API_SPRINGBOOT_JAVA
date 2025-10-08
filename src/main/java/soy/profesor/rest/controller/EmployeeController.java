package soy.profesor.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import soy.profesor.rest.service.EmployeeService;
import soy.profesor.rest.dto.NewEmployeeDTO;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmployeeController {

    // Atributos
    private final EmployeeService employeeService;

    // Métodos

    // Endpoint GET /api/private/employee
    @GetMapping("/private/employee")
    public List<NewEmployeeDTO> index() {
        return employeeService.getAll();
    }

    // Endpoint GET /api/private/employee/{DNI}
    @GetMapping("/private/employee/{DNI}")
    public NewEmployeeDTO show(@PathVariable String DNI) {
        return employeeService.getByDNI(DNI);
    }

    // Endpoint POST /api/employee (público o para registro)
    @PostMapping("/employee")
    public ResponseEntity<NewEmployeeDTO> store(@RequestBody NewEmployeeDTO employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.add(employee));
    }

    // Endpoint PUT /api/private/employee/{DNI}
    @PutMapping("/private/employee/{DNI}")
    public NewEmployeeDTO update(@RequestBody NewEmployeeDTO employeeData, @PathVariable String DNI) {
        return employeeService.edit(employeeData, DNI);
    }

    // Endpoint DELETE /api/private/employee/{DNI}
    @DeleteMapping("/private/employee/{DNI}")
    public NewEmployeeDTO destroy(@PathVariable String DNI) {
        return employeeService.delete(DNI);
    }
}
