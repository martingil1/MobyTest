package moby.evaluacion.services;

import moby.evaluacion.models.Candidato;
import moby.evaluacion.models.Tecnologia;
import moby.evaluacion.models.dtos.CandidatoDTO;
import moby.evaluacion.models.dtos.TecnologiaDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.models.responses.TecnologiaResponse;
import moby.evaluacion.repositories.CandidatoRepository;
import moby.evaluacion.repositories.TecnologiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TecnologiaServiceTest {

    @InjectMocks
    TecnologiaService tecnologiaService;

    @Mock
    TecnologiaRepository tecnologiaRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        tecnologiaService = new TecnologiaService(tecnologiaRepository);
    }

    @Test
    void testCrearOk() {

        Tecnologia tecnologia = Tecnologia.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        TecnologiaDTO tecnologiaDTO = TecnologiaDTO.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();

        when(tecnologiaRepository.save(tecnologia)).thenReturn(tecnologia);
        assertEquals(tecnologiaResponse, tecnologiaService.crear(tecnologiaDTO));
        verify(tecnologiaRepository,times(1)).save(tecnologia);
    }

    @Test
    void testEliminarOk() {

        Tecnologia tecnologia = Tecnologia.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();

        when(tecnologiaRepository.getById(1L)).thenReturn(tecnologia);
        assertEquals(tecnologiaResponse, tecnologiaService.eliminar(1L));
        verify(tecnologiaRepository,times(1)).delete(tecnologia);

    }

    @Test
    void testModificarOk(){

        Tecnologia tecnologia = Tecnologia.builder()
                .idTecnologia(1L)
                .nombre("Java")
                .version("1.8")
                .build();
        TecnologiaDTO tecnologiaDTO = TecnologiaDTO.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();

        when(tecnologiaRepository.findByNombreAndVersion("Java","1.8")).thenReturn(Optional.ofNullable(tecnologia));
        when(tecnologiaRepository.save(Objects.requireNonNull(tecnologia))).thenReturn(tecnologia);
        TecnologiaResponse tecnologiaResponseReturn = tecnologiaService.modificar(tecnologiaDTO,"Java","1.8");
        assertEquals(tecnologiaResponse, tecnologiaResponseReturn);
    }

    @Test
    void testMostrarOK(){

        Tecnologia tecnologia = Tecnologia.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        TecnologiaResponse tecnologiaResponse = TecnologiaResponse.builder()
                .nombre("Java")
                .version("1.8")
                .build();
        when(tecnologiaRepository.findByNombreAndVersion("Java","1.8")).thenReturn(Optional.ofNullable(tecnologia));
        assertEquals(tecnologiaResponse, tecnologiaService.mostrar("Java","1.8"));

    }
}
