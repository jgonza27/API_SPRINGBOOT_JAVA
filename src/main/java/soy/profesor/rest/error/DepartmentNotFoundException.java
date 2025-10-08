package soy.profesor.rest.error;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String message) {
        super(message);
    }

    public DepartmentNotFoundException(Long id) {
        super("No hay un departamento con ID-%d".formatted(id));
    }

    public DepartmentNotFoundException() {
        super("No hay departamentos con esos requisitos de búsqueda");
    }
}