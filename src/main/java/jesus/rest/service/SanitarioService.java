package jesus.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import jesus.rest.dto.SanitarioDTO;
import jesus.rest.error.SanitarioNotFoundException;
import jesus.rest.model.Sanitario;
import jesus.rest.repository.SanitarioJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SanitarioService {

	private final SanitarioJpaRepository sanitarioJpaRepository;

	// Crear un nuevo sanitario
	public SanitarioDTO create(SanitarioDTO sanitarioDTO) {
		Sanitario sanitario = convertToEntity(sanitarioDTO);
		Sanitario savedSanitario = sanitarioJpaRepository.save(sanitario);
		return convertToDTO(savedSanitario);
	}

	// Actualizar un sanitario existente
	public SanitarioDTO update(Long id, SanitarioDTO sanitarioDTO) {
		Sanitario existingSanitario = sanitarioJpaRepository.findById(id)
				.orElseThrow(() -> new SanitarioNotFoundException("Sanitario no encontrado con ID: " + id));
		existingSanitario.setName(sanitarioDTO.name());
		existingSanitario.setEspecialidad(sanitarioDTO.especialidad());
		Sanitario updatedSanitario = sanitarioJpaRepository.save(existingSanitario);
		return convertToDTO(updatedSanitario);
	}

	// Eliminar un sanitario por ID
	public void delete(Long id) {
		Sanitario sanitario = sanitarioJpaRepository.findById(id)
				.orElseThrow(() -> new SanitarioNotFoundException("Sanitario no encontrado con ID: " + id));
		sanitarioJpaRepository.delete(sanitario);
	}

	// Obtener todos los sanitarios
	public List<SanitarioDTO> getAll() {
		return sanitarioJpaRepository.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}

	// Obtener un sanitario por ID
	public SanitarioDTO getById(Long id) {
		Sanitario sanitario = sanitarioJpaRepository.findById(id)
				.orElseThrow(() -> new SanitarioNotFoundException("Sanitario no encontrado con ID: " + id));
		return convertToDTO(sanitario);
	}

	// Convertir entidad a DTO
	private SanitarioDTO convertToDTO(Sanitario sanitario) {
		return new SanitarioDTO(
				sanitario.getId(),
				sanitario.getName(),
				sanitario.getEspecialidad(),
				sanitario.getCitas() != null ? sanitario.getCitas().stream().map(cita -> cita.getId()).toList() : null);
	}

	// Convertir DTO a entidad
	private Sanitario convertToEntity(SanitarioDTO sanitarioDTO) {
		Sanitario sanitario = new Sanitario();
		sanitario.setId(sanitarioDTO.id());
		sanitario.setName(sanitarioDTO.name());
		sanitario.setEspecialidad(sanitarioDTO.especialidad());
		return sanitario;
	}
}