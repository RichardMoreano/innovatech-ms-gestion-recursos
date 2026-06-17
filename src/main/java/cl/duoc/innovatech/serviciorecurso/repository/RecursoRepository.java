package cl.duoc.innovatech.serviciorecurso.repository;

import cl.duoc.innovatech.serviciorecurso.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    List<Recurso> findByDisponibilidad(String disponibilidad);
}