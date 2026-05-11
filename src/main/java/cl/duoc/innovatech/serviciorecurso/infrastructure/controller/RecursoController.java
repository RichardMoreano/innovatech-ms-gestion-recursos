package cl.duoc.innovatech.serviciorecurso.infrastructure.controller;

import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoRequest;
import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoResponse;
import cl.duoc.innovatech.serviciorecurso.aplication.service.RecursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
@RequiredArgsConstructor
public class RecursoController {

    private final RecursoService recursoService;

    @PostMapping
    public ResponseEntity<RecursoResponse> crear(@RequestBody RecursoRequest request) {
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
}
