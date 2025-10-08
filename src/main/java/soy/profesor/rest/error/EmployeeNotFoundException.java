package soy.profesor.rest.error;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String dni) {
        super("No hay un empleado con DNI = " + dni);
    }

    public EmployeeNotFoundException() {
        super("No hay empleados con esos requisitos de búsqueda");
    }
}
