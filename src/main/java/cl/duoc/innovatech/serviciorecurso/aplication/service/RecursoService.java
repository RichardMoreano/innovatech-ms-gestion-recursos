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
