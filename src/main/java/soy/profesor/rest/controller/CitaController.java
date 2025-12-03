package soy.profesor.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soy.profesor.rest.dto.CitaDTO;
import soy.profesor.rest.service.CitaService;
import jakarta.validation.Valid; 


import java.util.List;

@RestController
@RequestMapping("/api/private/citas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // CORREGIDO
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public List<CitaDTO> getAllCitas() {
        return citaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> getCitaById(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CitaDTO> createCita(@Valid @RequestBody CitaDTO citaDTO) {
        return ResponseEntity.ok(citaService.create(citaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> updateCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        return ResponseEntity.ok(citaService.update(id, citaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}