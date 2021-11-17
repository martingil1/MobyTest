package moby.evaluacion.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import moby.evaluacion.models.dtos.ExperienciaDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.services.ExperienciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExperienciaControllerTest {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    ExperienciaController experienciaController;

    @Mock
    ExperienciaService experienciaService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(experienciaController).build();
    }

    @Test
    void testCargarCandidatoOk() throws Exception {

        ExperienciaDTO experienciaDTO = ExperienciaDTO.builder()
                .candidatoId(1L)
                .tecnologiaId(1L)
                .experiencia("3")
                .build();

        String jsonRequest = "{\"candidato_id\":\"1\", \"tecnologia_id\":\"1\" ,\"experiencia\":\"3\"}";
        doNothing().when(experienciaService).cargarExp(experienciaDTO);
        mockMvc.perform(post("/exp/cargar-experiencia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    void testCandidatosPorTecnologiaOk() throws Exception {

        List<CandidatoResponse> candidatoResponse = Collections.emptyList();
        when(experienciaService.candidatosPorTecnologia("Java","1.8")).thenReturn(candidatoResponse);

        MvcResult mvcResult = mockMvc.perform(get("/exp/candidatos-tecnologia/{nombre-tec}/{version}", "Java","1.8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(candidatoResponse), response);
    }

}
