package cl.duoc.innovatech.serviciorecurso.controller;

import cl.duoc.innovatech.serviciorecurso.dto.RecursoRequestDTO;
import cl.duoc.innovatech.serviciorecurso.dto.RecursoResponseDTO;
import cl.duoc.innovatech.serviciorecurso.service.RecursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2/recursos")
public class RecursoController {

    private final RecursoService recursoService;

    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }

    @PostMapping
    public ResponseEntity<RecursoResponseDTO> crear(@Valid @RequestBody RecursoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recursoService.crear(request));
    }

    @GetMapping
    public ResponseEntity<List<RecursoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(recursoService.obtenerTodos());
    }

    @GetMapping("/disponibilidad/{estado}")
    public ResponseEntity<List<RecursoResponseDTO>> obtenerPorDisponibilidad(@PathVariable String estado) {
        return ResponseEntity.ok(recursoService.obtenerPorDisponibilidad(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(recursoService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RecursoRequestDTO request) {
        return ResponseEntity.ok(recursoService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}