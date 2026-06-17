package cl.duoc.innovatech.serviciorecurso.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recursos")
@Data
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

    @Column(nullable = false, unique = true)
    private String email;

    private String rol;           // DESARROLLADOR, QA, DEVOPS, PM
    private String disponibilidad; // DISPONIBLE, OCUPADO, VACACIONES
    private LocalDateTime fechaContratacion;
    private Integer horasSemana;
}