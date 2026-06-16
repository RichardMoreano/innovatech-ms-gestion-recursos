package cl.duoc.innovatech.serviciorecurso.aplication.service;

import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoRequest;
import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoResponse;
import cl.duoc.innovatech.serviciorecurso.domain.entity.Recurso;
import cl.duoc.innovatech.serviciorecurso.domain.factory.RecursoFactory;
import cl.duoc.innovatech.serviciorecurso.infrastructure.repository.RecursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecursoService {

    private final RecursoRepository repository;
    private final RecursoFactory factory;

    public RecursoResponse crear(RecursoRequest request) {
        Recurso recurso = factory.crearRecurso(
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                request.getRol(),
                request.getHorasSemana()
        );
        Recurso guardado = repository.save(recurso);
        return mapToResponse(guardado);
    }

    public List<RecursoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<RecursoResponse> listarPorDisponibilidad(String disponibilidad) {
        return repository.findByDisponibilidad(disponibilidad).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public RecursoResponse obtenerPorId(Long id) {
        return repository.findById(id)
                .map(this::mapToResponse)
                .orElse(null);
    }

    public RecursoResponse actualizar(Long id, RecursoRequest request) {
        return repository.findById(id).map(existing -> {
            existing.setNombre(request.getNombre());
            existing.setApellido(request.getApellido());
            existing.setEmail(request.getEmail());
            existing.setRol(request.getRol());
            existing.setHorasSemana(request.getHorasSemana());
            Recurso saved = repository.save(existing);
            return mapToResponse(saved);
        }).orElse(null);
    }

    public boolean eliminar(Long id) {
        return repository.findById(id).map(existing -> {
            repository.delete(existing);
            return true;
        }).orElse(false);
    }

    private RecursoResponse mapToResponse(Recurso recurso) {
        return RecursoResponse.builder()
                .id(recurso.getId())
                .nombre(recurso.getNombre())
                .apellido(recurso.getApellido())
                .email(recurso.getEmail())
                .rol(recurso.getRol())
                .disponibilidad(recurso.getDisponibilidad())
                .fechaContratacion(recurso.getFechaContratacion())
                .horasSemana(recurso.getHorasSemana())
                .build();
    }
}
