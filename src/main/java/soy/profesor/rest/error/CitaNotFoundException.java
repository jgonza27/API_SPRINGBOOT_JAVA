package soy.profesor.rest.error;

public class CitaNotFoundException extends RuntimeException {

    // Constructor para manejar excepciones con un ID específico
    public CitaNotFoundException(Long id) {
        super("No se encontró una cita con ID = " + id);
    }

    // Constructor para manejar excepciones genéricas
    public CitaNotFoundException(String message) {
        super(message);
    }
}