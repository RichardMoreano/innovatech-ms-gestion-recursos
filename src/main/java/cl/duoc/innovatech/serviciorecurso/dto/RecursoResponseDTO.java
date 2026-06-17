package cl.duoc.innovatech.serviciorecurso.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RecursoResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String disponibilidad;
    private LocalDateTime fechaContratacion;
    private Integer horasSemana;
}