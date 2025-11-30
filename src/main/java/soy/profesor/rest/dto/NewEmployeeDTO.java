package soy.profesor.rest.dto;

import soy.profesor.rest.model.Employee;

public record NewEmployeeDTO(
        String dni,
        String name,
        String username,
        String password,
        Long id_department) {
    public static NewEmployeeDTO of(Employee e) {
        return new NewEmployeeDTO(
                e.getDni(),
                e.getName(),
                e.getUsername(),
                e.getPassword(),
                e.getDepartment() != null ? e.getDepartment().getId() : null);
    }
}