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
@CrossOrigin(originPatterns = "[*]")
public class SanitarioController {

	private final SanitarioService sanitarioService;

	// GET /api/private/sanitarios
	@GetMapping
	public List<SanitarioDTO> getAllSanitarios() {
		return sanitarioService.getAll();
	}

	// GET /api/private/sanitarios/{id}
	@GetMapping("/{id}")
	public ResponseEntity<SanitarioDTO> getSanitarioById(@PathVariable Long id) {
		return ResponseEntity.ok(sanitarioService.getById(id));
	}

	// POST /api/private/sanitarios
	@PostMapping
	public ResponseEntity<SanitarioDTO> createSanitario(@RequestBody SanitarioDTO sanitarioDTO) {
		return ResponseEntity.ok(sanitarioService.create(sanitarioDTO));
	}

	// PUT /api/private/sanitarios/{id}
	@PutMapping("/{id}")
	public ResponseEntity<SanitarioDTO> updateSanitario(@PathVariable Long id, @RequestBody SanitarioDTO sanitarioDTO) {
		return ResponseEntity.ok(sanitarioService.update(id, sanitarioDTO));
	}
}