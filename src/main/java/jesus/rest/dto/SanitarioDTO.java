package jesus.rest.dto;

import java.util.List;

import jesus.rest.model.Sanitario;

public record SanitarioDTO(Long id, String name, String especialidad, List<Long> citasIds) {

	// Método estático para convertir una entidad Sanitario a un DTO
	public static SanitarioDTO of(Sanitario sanitario) {
		return new SanitarioDTO(
				sanitario.getId(),
				sanitario.getName(),
				sanitario.getEspecialidad(),
				sanitario.getCitas() != null ? sanitario.getCitas().stream().map(cita -> cita.getId()).toList() : null);
	}
}