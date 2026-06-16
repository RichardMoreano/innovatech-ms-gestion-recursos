package cl.duoc.innovatech.serviciorecurso.infrastructure.controller;

import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoRequest;
import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoResponse;
import cl.duoc.innovatech.serviciorecurso.aplication.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import cl.duoc.innovatech.serviciorecurso.aplication.exception.RecursoNotFoundException;

@RestController
@RequestMapping("/api/recursos")
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService recursoService;

    @PostMapping
    public ResponseEntity<RecursoResponse> crear(@RequestHeader(value = "X-User-Id", required = false) String userId,
                                                 @RequestHeader(value = "X-User-Roles", required = false) String roles,
                                                 @RequestBody RecursoRequest request) {
        // Comentario estilo estudiante: acá recibo los headers que inyecta la gateway
        RecursoResponse response = recursoService.crear(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RecursoResponse>> listarTodos() {
        return ResponseEntity.ok(recursoService.listarTodos());
    }

    @GetMapping("/disponibilidad/{estado}")
    public ResponseEntity<List<RecursoResponse>> listarPorDisponibilidad(@PathVariable String estado) {
        return ResponseEntity.ok(recursoService.listarPorDisponibilidad(estado));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoResponse> obtenerPorId(@PathVariable Long id,
                                                       @RequestHeader(value = "X-User-Id", required = false) String userId) {
        RecursoResponse r = recursoService.obtenerPorId(id);
        return ResponseEntity.ok(r);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoResponse> actualizar(@PathVariable Long id,
                                                      @RequestBody RecursoRequest request,
                                                      @RequestHeader(value = "X-User-Id", required = false) String userId) {
        RecursoResponse r = recursoService.actualizar(id, request);
        return ResponseEntity.ok(r);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id,
                                      @RequestHeader(value = "X-User-Id", required = false) String userId) {
        recursoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RecursoNotFoundException.class)
    public ResponseEntity<String> handleNotFound(RecursoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
