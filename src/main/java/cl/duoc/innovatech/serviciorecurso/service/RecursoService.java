package cl.duoc.innovatech.serviciorecurso.service;

import cl.duoc.innovatech.serviciorecurso.dto.RecursoRequestDTO;
import cl.duoc.innovatech.serviciorecurso.dto.RecursoResponseDTO;
import java.util.List;

public interface RecursoService {
    RecursoResponseDTO crear(RecursoRequestDTO request);
    List<RecursoResponseDTO> obtenerTodos();
    List<RecursoResponseDTO> obtenerPorDisponibilidad(String estado);
    RecursoResponseDTO obtenerPorId(Long id);
    RecursoResponseDTO actualizar(Long id, RecursoRequestDTO request);
    void eliminar(Long id);
    void actualizarDisponibilidad(Long id, String disponibilidad);
}