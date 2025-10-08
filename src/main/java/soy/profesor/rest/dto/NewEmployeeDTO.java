package soy.profesor.rest.dto;

import soy.profesor.rest.model.Employee;

public record NewEmployeeDTO(
        String dni,
        String name,
        // String login,
        String username,
        String password,
        Long id_departament) {

    public static NewEmployeeDTO of(Employee e) {
        return new NewEmployeeDTO(
                e.getDni(),
                e.getName(),
                // e.getLogin(),
                e.getUsername(),
                e.getPassword(),
                e.getDepartment().getId());
    }
}
