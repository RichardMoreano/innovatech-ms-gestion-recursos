package cl.duoc.innovatech.serviciorecurso.infrastructure.repository;

import cl.duoc.innovatech.serviciorecurso.domain.entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

    List<Recurso> findByDisponibilidad(String disponibilidad);
    List<Recurso> findByRol(String rol);
}
