package soy.profesor.rest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soy.profesor.rest.dto.CitaDTO;
import soy.profesor.rest.error.CitaNotFoundException;
import soy.profesor.rest.model.Cita;
import soy.profesor.rest.model.Paciente;
import soy.profesor.rest.model.Sanitario;
import soy.profesor.rest.repository.CitaJpaRepository;
import soy.profesor.rest.repository.PacienteJpaRepository;
import soy.profesor.rest.repository.SanitarioJpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaJpaRepository citaJpaRepository;
    private final PacienteJpaRepository pacienteJpaRepository; // Repositorio para manejar pacientes
    private final SanitarioJpaRepository sanitarioJpaRepository; // Repositorio para manejar sanitarios

    // Obtener una cita por su ID
    public CitaDTO getById(Long id) {
        Cita cita = citaJpaRepository.findById(id)
                .orElseThrow(() -> new CitaNotFoundException("Cita no encontrada con ID: " + id));
        return convertToDTO(cita);
    }

    // Crear una nueva cita
    public CitaDTO create(CitaDTO citaDTO) {
        Cita cita = convertToEntity(citaDTO);
        Cita savedCita = citaJpaRepository.save(cita);
        return convertToDTO(savedCita);
    }

    // Actualizar una cita existente
    public CitaDTO update(Long id, CitaDTO citaDTO) {
        Cita existingCita = citaJpaRepository.findById(id)
                .orElseThrow(() -> new CitaNotFoundException("Cita no encontrada con ID: " + id));

        // Actualizar los campos básicos
        existingCita.setFechaHora(citaDTO.fechaHora());
        existingCita.setSintomas(citaDTO.sintomas());

        // Actualizar la relación con el paciente
        Paciente paciente = pacienteJpaRepository.findById(citaDTO.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + citaDTO.pacienteId()));
        existingCita.setPaciente(paciente);

        // Actualizar la relación con los sanitarios
        List<Sanitario> sanitarios = sanitarioJpaRepository.findAllById(citaDTO.sanitariosIds());
        if (sanitarios.isEmpty() && !citaDTO.sanitariosIds().isEmpty()) {
            throw new RuntimeException("No se encontraron sanitarios con los IDs proporcionados");
        }
        existingCita.setSanitarios(sanitarios);

        Cita updatedCita = citaJpaRepository.save(existingCita);
        return convertToDTO(updatedCita);
    }

    // Eliminar una cita por su ID
    public void delete(Long id) {
        Cita cita = citaJpaRepository.findById(id)
                .orElseThrow(() -> new CitaNotFoundException("Cita no encontrada con ID: " + id));
        citaJpaRepository.delete(cita);
    }

    // Obtener todas las citas
    public List<CitaDTO> getAll() {
        return citaJpaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convertir entidad a DTO
    private CitaDTO convertToDTO(Cita cita) {
        return new CitaDTO(
                cita.getId(),
                cita.getFechaHora(),
                cita.getSintomas(),
                cita.getPaciente().getId(),
                cita.getSanitarios() != null ? cita.getSanitarios().stream().map(Sanitario::getId).toList() : null);
    }

    // Convertir DTO a entidad
    private Cita convertToEntity(CitaDTO citaDTO) {
        Cita cita = new Cita();
        cita.setId(citaDTO.id());
        cita.setFechaHora(citaDTO.fechaHora());
        cita.setSintomas(citaDTO.sintomas());

        // Relación con el paciente
        Paciente paciente = pacienteJpaRepository.findById(citaDTO.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + citaDTO.pacienteId()));
        cita.setPaciente(paciente);

        // Relación con los sanitarios
        List<Sanitario> sanitarios = sanitarioJpaRepository.findAllById(citaDTO.sanitariosIds());
        if (sanitarios.isEmpty() && !citaDTO.sanitariosIds().isEmpty()) {
            throw new RuntimeException("No se encontraron sanitarios con los IDs proporcionados");
        }
        cita.setSanitarios(sanitarios);

        return cita;
    }
}