package cl.duoc.innovatech.serviciorecurso.infrastructure.controller;

import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoRequest;
import cl.duoc.innovatech.serviciorecurso.aplication.dto.RecursoResponse;
import cl.duoc.innovatech.serviciorecurso.aplication.service.RecursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Pruebas del controlador Recurso con MockMvc standalone, estilo estudiante
class RecursoControllerTest {

    private MockMvc mockMvc;
    private RecursoService servicio;

    @BeforeEach
    void setUp() {
        // implemento un servicio simple por proxy para simular la lógica sin Mockito
        servicio = new RecursoService(null, null) {
            @Override
            public RecursoResponse crear(RecursoRequest request) {
                return RecursoResponse.builder()
                        .id(1L)
                        .nombre(request.getNombre())
                        .apellido(request.getApellido())
                        .email(request.getEmail())
                        .rol(request.getRol())
                        .disponibilidad("DISPONIBLE")
                        .horasSemana(request.getHorasSemana())
                        .build();
            }

            @Override
            public java.util.List<RecursoResponse> listarTodos() {
                return Arrays.asList(crear(new RecursoRequest("Ana","Perez","a@a.cl","DEV",40)));
            }

            @Override
            public java.util.List<RecursoResponse> listarPorDisponibilidad(String disponibilidad) {
                if ("DISPONIBLE".equalsIgnoreCase(disponibilidad)) return listarTodos();
                return Collections.emptyList();
            }

            @Override
            public RecursoResponse obtenerPorId(Long id) {
                if (id == 1L) return crear(new RecursoRequest("Ana","Perez","a@a.cl","DEV",40));
                throw new cl.duoc.innovatech.serviciorecurso.aplication.exception.RecursoNotFoundException(id);
            }

            @Override
            public RecursoResponse actualizar(Long id, RecursoRequest request) {
                if (id == 1L) return crear(request);
                throw new cl.duoc.innovatech.serviciorecurso.aplication.exception.RecursoNotFoundException(id);
            }

            @Override
            public boolean eliminar(Long id) {
                if (id == 1L) return true;
                throw new cl.duoc.innovatech.serviciorecurso.aplication.exception.RecursoNotFoundException(id);
            }
        };

        // montamos el controlador con el servicio fake
        RecursoController controller = new RecursoController(servicio);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCrearRecursoExitoso() throws Exception {
        String body = "{\"nombre\":\"Juan\",\"apellido\":\"Lopez\",\"email\":\"j@j.cl\",\"rol\":\"QA\",\"horasSemana\":20}";

        mockMvc.perform(post("/api/recursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-User-Id", "123")
                        .header("X-User-Roles", "ROLE_USER")
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", is("Juan")));
    }

    @Test
    void testListarTodosDevuelveLista() throws Exception {
        mockMvc.perform(get("/api/recursos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].nombre", is("Ana")));
    }

    @Test
    void testObtenerPorIdNoExisteDevuelve404() throws Exception {
        mockMvc.perform(get("/api/recursos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testActualizarRecursoNoEncontradoRetornaCuatroCientosCuatro() throws Exception {
        String body = "{\"nombre\":\"Pedro\",\"apellido\":\"Gomez\",\"email\":\"p@p.cl\",\"rol\":\"DEV\",\"horasSemana\":35}";

        mockMvc.perform(put("/api/recursos/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-User-Id", "1")
                        .content(body))
                .andExpect(status().isNotFound());
    }

    @Test
    void testObtenerPorIdExisteDevuelve200() throws Exception {
        mockMvc.perform(get("/api/recursos/1").header("X-User-Id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Ana")));
    }

    @Test
    void testActualizarRecursoExitoso() throws Exception {
        String body = "{\"nombre\":\"Pedro\",\"apellido\":\"Gomez\",\"email\":\"p@p.cl\",\"rol\":\"DEV\",\"horasSemana\":35}";

        mockMvc.perform(put("/api/recursos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-User-Id", "1")
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Pedro")));
    }

    @Test
    void testEliminarRecursoNoEncontradoRetornaCuatroCientosCuatro() throws Exception {
        mockMvc.perform(delete("/api/recursos/999").header("X-User-Id", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testEliminarRecursoExitosoRetorna204() throws Exception {
        mockMvc.perform(delete("/api/recursos/1").header("X-User-Id", "1"))
                .andExpect(status().isNoContent());
    }
}
