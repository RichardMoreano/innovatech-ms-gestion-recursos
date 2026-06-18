package cl.duoc.innovatech.serviciorecurso.service.impl;

import cl.duoc.innovatech.serviciorecurso.dto.RecursoRequestDTO;
import cl.duoc.innovatech.serviciorecurso.dto.RecursoResponseDTO;
import cl.duoc.innovatech.serviciorecurso.model.Recurso;
import cl.duoc.innovatech.serviciorecurso.repository.RecursoRepository;
import cl.duoc.innovatech.serviciorecurso.service.RecursoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecursoServiceImpl implements RecursoService {

    private final RecursoRepository recursoRepository;

    public RecursoServiceImpl(RecursoRepository recursoRepository) {
        this.recursoRepository = recursoRepository;
    }

    @Override
    public RecursoResponseDTO crear(RecursoRequestDTO request) {
        Recurso recurso = Recurso.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .rol(request.getRol())
                .disponibilidad(request.getDisponibilidad() != null ? request.getDisponibilidad() : "DISPONIBLE")
                .fechaContratacion(LocalDateTime.now())
                .horasSemana(request.getHorasSemana())
                .build();
        return mapToDTO(recursoRepository.save(recurso));
    }

    @Override
    public List<RecursoResponseDTO> obtenerTodos() {
        return recursoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<RecursoResponseDTO> obtenerPorDisponibilidad(String estado) {
        return recursoRepository.findByDisponibilidad(estado).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public RecursoResponseDTO obtenerPorId(Long id) {
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + id));
        return mapToDTO(recurso);
    }

    @Override
    public RecursoResponseDTO actualizar(Long id, RecursoRequestDTO request) {
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + id));
        
        recurso.setNombre(request.getNombre());
        recurso.setApellido(request.getApellido());
        recurso.setEmail(request.getEmail());
        recurso.setRol(request.getRol());
        recurso.setDisponibilidad(request.getDisponibilidad());
        recurso.setHorasSemana(request.getHorasSemana());
        
        return mapToDTO(recursoRepository.save(recurso));
    }

    @Override
    public void eliminar(Long id) {
        if (!recursoRepository.existsById(id)) {
            throw new RuntimeException("Recurso no encontrado con ID: " + id);
        }
        recursoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void actualizarDisponibilidad(Long id, String disponibilidad) {
        Recurso recurso = recursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recurso no encontrado con ID: " + id));
        recurso.setDisponibilidad(disponibilidad);
        recursoRepository.save(recurso);
    }

    private RecursoResponseDTO mapToDTO(Recurso recurso) {
        RecursoResponseDTO dto = new RecursoResponseDTO();
        dto.setId(recurso.getId());
        dto.setNombre(recurso.getNombre());
        dto.setApellido(recurso.getApellido());
        dto.setEmail(recurso.getEmail());
        dto.setRol(recurso.getRol());
        dto.setDisponibilidad(recurso.getDisponibilidad());
        dto.setFechaContratacion(recurso.getFechaContratacion());
        dto.setHorasSemana(recurso.getHorasSemana());
        return dto;
    }
}