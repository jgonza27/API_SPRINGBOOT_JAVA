package soy.profesor.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soy.profesor.rest.dto.CitaDTO;
import soy.profesor.rest.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/api/private/citas")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "[*]")
public class CitaController {

    private final CitaService citaService;

    // GET /api/private/citas
    @GetMapping
    public List<CitaDTO> getAllCitas() {
        return citaService.getAll();
    }

    // GET /api/private/citas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> getCitaById(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.getById(id));
    }

    // POST /api/private/citas
    @PostMapping
    public ResponseEntity<CitaDTO> createCita(@RequestBody CitaDTO citaDTO) {
        return ResponseEntity.ok(citaService.create(citaDTO));
    }

    // PUT /api/private/citas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> updateCita(@PathVariable Long id, @RequestBody CitaDTO citaDTO) {
        return ResponseEntity.ok(citaService.update(id, citaDTO));
    }

    // DELETE /api/private/citas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}