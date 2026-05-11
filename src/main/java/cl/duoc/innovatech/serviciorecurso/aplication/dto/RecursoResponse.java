package cl.duoc.innovatech.serviciorecurso.aplication.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecursoResponse {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private String disponibilidad;
    private LocalDateTime fechaContratacion;
    private Integer horasSemana;
}
