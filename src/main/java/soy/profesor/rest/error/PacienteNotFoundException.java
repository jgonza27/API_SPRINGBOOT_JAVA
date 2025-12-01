package soy.profesor.rest.error;

public class PacienteNotFoundException extends RuntimeException {

    public PacienteNotFoundException(String message) {
        super(message);
    }

    public PacienteNotFoundException(Long id) {
        super("No se encontró un paciente con ID-%d".formatted(id));
    }

    public PacienteNotFoundException() {
        super("No se encontraron pacientes con los criterios de búsqueda especificados");
    }
}