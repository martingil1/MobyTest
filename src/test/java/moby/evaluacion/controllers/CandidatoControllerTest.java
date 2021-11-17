package moby.evaluacion.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import moby.evaluacion.models.dtos.CandidatoDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.services.CandidatoService;
import moby.evaluacion.services.CandidatoServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CandidatoControllerTest {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    CandidatoController candidatoController;

    @Mock
    CandidatoService candidatoService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(candidatoController).build();
    }

    @Test
    void testCargarCandidatoOk() throws Exception {

        CandidatoDTO candidatoDTO = CandidatoDTO.builder()
                .nombre("Juan")
                .apellido("Perez")
                .tipoDni("dni")
                .numeroDni("12345678")
                .fechaNacimiento(LocalDate.of(1997,8,30))
                .build();
        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .experiencia("3")
                .build();

        String jsonRequest = "{\"nombre\":\"Juan\", \"apellido\":\"Perez\" ," +
                "\"tipo_dni\":\"dni\", \"numero_dni\":\"12345678\", \"fecha_nacimiento\":\"22/10/1996\"}";
        when(candidatoService.crear(candidatoDTO)).thenReturn(candidatoResponse);
        mockMvc.perform(post("/candidato/cargar-candidato")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    void testVerCandidatoOk() throws Exception {

        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .experiencia("3")
                .build();
        when(candidatoService.mostrar("12345678")).thenReturn(candidatoResponse);

        MvcResult mvcResult = mockMvc.perform(get("/candidato/mostrar-candidato/{dni}", 12345678)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(candidatoResponse), response);
    }

    @Test
    void testModificarCandidatoOk() throws Exception {

        CandidatoDTO candidatoDTO = CandidatoDTO.builder()
                .nombre("Juan")
                .apellido("Perez")
                .tipoDni("dni")
                .numeroDni("12345678")
                .fechaNacimiento(LocalDate.of(1997,8,30))
                .build();
        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .experiencia("3")
                .build();

        String jsonRequest = "{\"nombre\":\"Java\", \"version\":\"1.8\"}";
        when(candidatoService.modificar(candidatoDTO,"12345678")).thenReturn(candidatoResponse);

        mockMvc.perform(post("/candidato/modificar-candidato/{dni}", "12345678")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarCandidatoOk() throws Exception {

        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .experiencia("3")
                .build();

        when(candidatoService.eliminar("12345678")).thenReturn(candidatoResponse);

        mockMvc.perform(delete("/candidato/eliminar-candidato/{dni}", "12345678")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
