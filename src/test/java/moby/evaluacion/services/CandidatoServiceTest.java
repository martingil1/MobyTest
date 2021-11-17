package moby.evaluacion.services;

import moby.evaluacion.models.Candidato;
import moby.evaluacion.models.dtos.CandidatoDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.repositories.CandidatoRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class CandidatoServiceTest {

    @InjectMocks
    CandidatoService candidatoService;

    @Mock
    CandidatoRepository candidatoRepository;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        candidatoService = new CandidatoService(candidatoRepository);
    }

    @Test
    void testCrearOk() {

        Candidato candidato = Candidato.builder()
                .idCandidato(1L)
                .nombre("Juan")
                .apellido("Perez")
                .tipoDoc("dni")
                .dni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();
        CandidatoDTO candidatoDTO = CandidatoDTO.builder()
                .nombre("Juan")
                .apellido("Perez")
                .tipoDni("dni")
                .numeroDni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();
        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .build();

        when(candidatoRepository.existsByDni("12345678")).thenReturn(false);
        when(candidatoService.construirObjeto(candidatoDTO)).thenReturn(candidato);

        assertEquals(candidatoResponse, candidatoService.crear(candidatoDTO));
        verify(candidatoRepository,times(1)).existsByDni("12345678");
    }

    @Test
    void testConstruirObjetoOk() {

        CandidatoDTO candidatoDTO = CandidatoDTO.builder()
                .nombre("Juan")
                .apellido("Perez")
                .tipoDni("dni")
                .numeroDni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();
        Candidato candidato = Candidato.builder()
                .idCandidato(1L)
                .nombre("Juan")
                .apellido("Perez")
                .tipoDoc("dni")
                .dni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();

        assertEquals(candidato, candidatoService.construirObjeto(candidatoDTO));
        verify(candidatoRepository,times(1)).save(candidato);

    }

    @Test
    void testEliminarOk() {

        Candidato candidato = Candidato.builder()
                .idCandidato(1L)
                .nombre("Juan")
                .apellido("Perez")
                .tipoDoc("dni")
                .dni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();

        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .build();

        when(candidatoRepository.findByDni("12345678")).thenReturn(Optional.ofNullable(candidato));
        assertEquals(candidatoResponse, candidatoService.eliminar("12345678"));
        verify(candidatoRepository,times(1)).delete(candidato);

    }

    @Test
    void testModificarCandidatoOk(){

        Candidato candidato = Candidato.builder()
                .idCandidato(1L)
                .nombre("Juan")
                .apellido("Perez")
                .tipoDoc("dni")
                .dni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();
        CandidatoDTO candidatoDTO = CandidatoDTO.builder()
                .nombre("Juan")
                .apellido("Perez")
                .tipoDni("dni")
                .numeroDni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();
        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .build();

        when(candidatoRepository.findByDni("12345678")).thenReturn(Optional.ofNullable(candidato));
        when(candidatoService.construirObjeto(candidatoDTO)).thenReturn(candidato);
        //verify(candidatoRepository,times(1)).delete(candidato);
        //verify(candidatoRepository,times(1)).save(candidato);
        assertEquals(candidatoResponse, candidatoService.modificar(candidatoDTO,"12345678"));

    }

    @Test
    void testMostrarOK(){

        Candidato candidato = Candidato.builder()
                .idCandidato(1L)
                .nombre("Juan")
                .apellido("Perez")
                .tipoDoc("dni")
                .dni("12345678")
                .fechaNacimiento(LocalDate.of(1997, 8, 30))
                .build();
        CandidatoResponse candidatoResponse = CandidatoResponse.builder()
                .nombre("Juan")
                .apellido("Perez")
                .build();
        when(candidatoRepository.findByDni("12345678")).thenReturn(Optional.ofNullable(candidato));
        when(candidatoService.mostrar("12345678")).thenReturn(candidatoResponse);
        assertEquals(candidatoResponse, candidatoService.mostrar("12345678"));

    }
}
