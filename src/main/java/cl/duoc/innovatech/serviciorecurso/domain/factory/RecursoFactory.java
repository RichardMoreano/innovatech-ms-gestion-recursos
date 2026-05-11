package cl.duoc.innovatech.serviciorecurso.domain.factory;

import cl.duoc.innovatech.serviciorecurso.domain.entity.Recurso;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RecursoFactory {

    public Recurso crearRecurso(String nombre, String apellido, String email, String rol, Integer horasSemana) {
        return Recurso.builder()
                .nombre(nombre)
                .apellido(apellido)
                .email(email)
                .rol(rol)
                .disponibilidad("DISPONIBLE")
                .fechaContratacion(LocalDateTime.now())
                .horasSemana(horasSemana)
                .build();
    }
}
