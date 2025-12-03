package jesus.rest.error;

public class SanitarioNotFoundException extends RuntimeException {

	public SanitarioNotFoundException(String message) {
		super(message);
	}

	public SanitarioNotFoundException(Long id) {
		super("No se encontró un sanitario con ID-" + id);
	}
}