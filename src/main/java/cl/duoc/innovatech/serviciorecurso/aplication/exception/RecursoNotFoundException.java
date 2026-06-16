package cl.duoc.innovatech.serviciorecurso.aplication.exception;

public class RecursoNotFoundException extends RuntimeException {
    public RecursoNotFoundException(Long id) {
        super("Recurso no encontrado: " + id);
    }
}
