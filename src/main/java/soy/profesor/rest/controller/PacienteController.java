package soy.profesor.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soy.profesor.rest.dto.PacienteDTO;
import soy.profesor.rest.service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/private/pacientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // CORREGIDO
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public List<PacienteDTO> getAllPacientes() {
        return pacienteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PacienteDTO> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.create(pacienteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return ResponseEntity.ok(pacienteService.update(id, pacienteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}