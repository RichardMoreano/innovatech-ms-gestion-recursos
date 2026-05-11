package cl.duoc.innovatech.serviciorecurso.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recursos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    private String email;
    private String rol;               // DESARROLLADOR, QA, DEVOPS, PM, etc.
    private String disponibilidad;    // DISPONIBLE, OCUPADO, VACACIONES

    private LocalDateTime fechaContratacion;
    private Integer horasSemana;
}
