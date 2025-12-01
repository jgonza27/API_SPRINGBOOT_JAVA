package soy.profesor.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soy.profesor.rest.dto.PacienteDTO;
import soy.profesor.rest.error.PacienteNotFoundException;
import soy.profesor.rest.model.Paciente;
import soy.profesor.rest.repository.PacienteJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteJpaRepository pacienteJpaRepository;

    // Obtener todos los pacientes
    public List<PacienteDTO> getAll() {
        return pacienteJpaRepository.findAll()
                .stream()
                .map(this::convertToDTO) // Convertir cada entidad a DTO
                .collect(Collectors.toList());
    }

    // Obtener un paciente por ID
    public PacienteDTO getById(Long id) {
        Paciente paciente = pacienteJpaRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente no encontrado con ID: " + id));
        return convertToDTO(paciente);
    }

    // Crear un nuevo paciente
    public PacienteDTO create(PacienteDTO pacienteDTO) {
        Paciente paciente = convertToEntity(pacienteDTO);
        Paciente savedPaciente = pacienteJpaRepository.save(paciente);
        return convertToDTO(savedPaciente);
    }

    // Actualizar un paciente existente
    public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
        Paciente existingPaciente = pacienteJpaRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente no encontrado con ID: " + id));
        existingPaciente.setNombre(pacienteDTO.nombre());
        existingPaciente.setAntecedentes(pacienteDTO.antecedentes());
        Paciente updatedPaciente = pacienteJpaRepository.save(existingPaciente);
        return convertToDTO(updatedPaciente);
    }

    // Eliminar un paciente por ID
    @Transactional
    public void delete(Long id) {
        Paciente paciente = pacienteJpaRepository.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente no encontrado con ID: " + id));
        pacienteJpaRepository.delete(paciente);
    }

    // Convertir entidad a DTO
    private PacienteDTO convertToDTO(Paciente paciente) {
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getAntecedentes());
    }

    // Convertir DTO a entidad
    private Paciente convertToEntity(PacienteDTO pacienteDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(pacienteDTO.id());
        paciente.setNombre(pacienteDTO.nombre());
        paciente.setAntecedentes(pacienteDTO.antecedentes());
        return paciente;
    }
}