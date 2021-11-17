package moby.evaluacion.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import moby.evaluacion.models.dtos.TecnologiaDTO;
import moby.evaluacion.models.responses.TecnologiaResponse;
import moby.evaluacion.services.TecnologiaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TecnologiaControllerTest {

    MockMvc mockMvc;

    @Autowired
    @InjectMocks
    TecnologiaController tecnologiaController;

    @Mock
    TecnologiaService tecnologiaService;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tecnologiaController).build();
    }

    @Test
    void testCargarTecnologiaOk() throws Exception {

        TecnologiaDTO tecnologiaDTO = TecnologiaDTO.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();

        String jsonRequest = "{\"nombre\":\"Java\", \"version\":\"1.8\"}";
        when(tecnologiaService.crear(tecnologiaDTO)).thenReturn(tecnologiaResponse);
        mockMvc.perform(post("/tecnologia/cargar-tecnologia")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    void testVerTecnologiaOk() throws Exception {

        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        when(tecnologiaService.mostrar("Java","1.8")).thenReturn(tecnologiaResponse);

        MvcResult mvcResult = mockMvc.perform(get("/tecnologia/mostrar-tecnologia/{nombre}/{version}", "Java","1.8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertEquals(new ObjectMapper().writeValueAsString(tecnologiaResponse), response);
    }

    @Test
    void testModificarTecnologiaOk() throws Exception {

        TecnologiaDTO tecnologiaDTO = TecnologiaDTO.builder()
                .nombre("Java")
                .version("1.8")
                .build();

        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();

        String jsonRequest = "{\"nombre\":\"Java\", \"version\":\"1.8\"}";
        when(tecnologiaService.modificar(tecnologiaDTO,"Java","1.8")).thenReturn(tecnologiaResponse);

        mockMvc.perform(post("/tecnologia/modificar-tecnologia/{nombre}/{version}", "Java","1.8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    void testEliminarTecnologiaOk() throws Exception {

        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();

        when(tecnologiaService.eliminar(1L)).thenReturn(tecnologiaResponse);

        mockMvc.perform(delete("/tecnologia/eliminar-tecnologia/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
