package cl.duoc.innovatech.serviciorecurso.aplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecursoRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String rol;
    private Integer horasSemana;
}
