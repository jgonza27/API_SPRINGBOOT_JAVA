package soy.profesor.rest.service;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soy.profesor.rest.dto.NewEmployeeDTO;
import soy.profesor.rest.error.DepartmentNotFoundException;
import soy.profesor.rest.error.EmployeeNotFoundException;
import soy.profesor.rest.model.Department;
import soy.profesor.rest.model.Employee;
import soy.profesor.rest.repository.EmployeeJpaRepository;
import soy.profesor.rest.repository.DepartmentJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    // Atributos
    private final EmployeeJpaRepository employeeJpaRepository;
    private final DepartmentJpaRepository departmentJpaRepository;

    private final PasswordEncoder passwordEncoder;

    // Métodos
    // public List<Employee> getAll() {
    public List<NewEmployeeDTO> getAll() {
        var result = employeeJpaRepository.findAll();
        return result.stream().map(employee -> {
            return NewEmployeeDTO.of(employee);
        }).collect(Collectors.toList());
    }

    // public Employee getByDNI(String DNI)
    public NewEmployeeDTO getByDNI(String DNI) {
        return employeeJpaRepository.findByDni(DNI).map(employee -> {
            return NewEmployeeDTO.of(employee);
        }).orElseThrow(() -> new EmployeeNotFoundException());
        // return employeeJpaRepository.findByDni(DNI).orElseThrow(() -> new
        // EmployeeNotFoundException(DNI));
    }

    public NewEmployeeDTO add(NewEmployeeDTO dto) {
        var result = employeeJpaRepository.findByName(dto.name());
        if (result.isPresent())
            throw new EmployeeNotFoundException("Ya existe un empleado con el nombre " + dto.name());
        Department refDpto = departmentJpaRepository.findById(dto.id_department())
                .orElseThrow(() -> new DepartmentNotFoundException(dto.id_department()));
        // var nuevoEmpleado =
        // Employee.builder().dni(dto.dni()).name(dto.name()).login(dto.login()).password(passwordEncoder.encode(dto.password())).department(refDpto).build();
        var nuevoEmpleado = Employee.builder().dni(dto.dni()).name(dto.name()).username(dto.username())
                .password(passwordEncoder.encode(dto.password())).department(refDpto).build();
        return NewEmployeeDTO.of(employeeJpaRepository.save(nuevoEmpleado));
    }

    public NewEmployeeDTO edit(NewEmployeeDTO dto, String DNI) {
        return employeeJpaRepository.findByDni(DNI).map(employee -> {
            employee.setDni(dto.dni());
            employee.setName(dto.name());
            employee.setUsername(dto.username());
            // employee.setLogin(dto.login());
            employee.setPassword(dto.password());
            Department refDpto = departmentJpaRepository.findById(dto.id_department())
                    .orElseThrow(() -> new DepartmentNotFoundException(dto.id_department()));
            employee.setDepartment(refDpto);
            return NewEmployeeDTO.of(employeeJpaRepository.save(employee));
        }).orElseThrow(() -> new EmployeeNotFoundException());
    }

    public NewEmployeeDTO delete(String DNI) {
        var result = employeeJpaRepository.findByDni(DNI);
        if (result.isEmpty())
            throw new EmployeeNotFoundException();
        employeeJpaRepository.deleteByDni(DNI);
        return NewEmployeeDTO.of(result.get());
    }

}
