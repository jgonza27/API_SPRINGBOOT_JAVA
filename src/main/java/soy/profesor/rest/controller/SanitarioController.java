package soy.profesor.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soy.profesor.rest.dto.SanitarioDTO;
import soy.profesor.rest.service.SanitarioService;

import java.util.List;

@RestController
@RequestMapping("/api/private/sanitarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // CORREGIDO
public class SanitarioController {

	private final SanitarioService sanitarioService;

	@GetMapping
	public List<SanitarioDTO> getAllSanitarios() {
		return sanitarioService.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SanitarioDTO> getSanitarioById(@PathVariable Long id) {
		return ResponseEntity.ok(sanitarioService.getById(id));
	}

	@PostMapping
	public ResponseEntity<SanitarioDTO> createSanitario(@RequestBody SanitarioDTO sanitarioDTO) {
		return ResponseEntity.ok(sanitarioService.create(sanitarioDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SanitarioDTO> updateSanitario(@PathVariable Long id, @RequestBody SanitarioDTO sanitarioDTO) {
		return ResponseEntity.ok(sanitarioService.update(id, sanitarioDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSanitario(@PathVariable Long id) {
		sanitarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
}