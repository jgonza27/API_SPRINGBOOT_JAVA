package jesus.rest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jesus.rest.dto.PacienteDTO;
import jesus.rest.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/private/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Pacientes", description = "API para gestión de pacientes")
@SecurityRequirement(name = "Bearer Authentication")
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    @Operation(summary = "Obtener todos los pacientes")
    public List<PacienteDTO> getAllPacientes() {
        return pacienteService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un paciente por ID")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.getById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo paciente")
    public ResponseEntity<PacienteDTO> createPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.create(pacienteDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un paciente existente")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.update(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un paciente")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}