package moby.evaluacion.services;

import lombok.RequiredArgsConstructor;
import moby.evaluacion.exceptions.CandidatoNotFoundException;
import moby.evaluacion.exceptions.DniDuplicateException;
import moby.evaluacion.models.Candidato;
import moby.evaluacion.models.dtos.CandidatoDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.repositories.CandidatoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;

    public CandidatoResponse crear(CandidatoDTO candidatoDTO) {

        if (candidatoRepository.existsByDni(candidatoDTO.getNumeroDni()))
            throw new DniDuplicateException();

        Candidato candidato = construirObjeto(candidatoDTO);

        return candidato.toResponse(candidato);
    }

    public Candidato construirObjeto(CandidatoDTO candidatoDTO) {

        return candidatoRepository.save(Candidato.builder()
                .nombre(candidatoDTO.getNombre())
                .apellido(candidatoDTO.getApellido())
                .tipoDoc(candidatoDTO.getTipoDni())
                .dni(candidatoDTO.getNumeroDni())
                .fechaNacimiento(candidatoDTO.getFechaNacimiento())
                .build());
    }

    public CandidatoResponse eliminar(String dni) {

        Candidato candidato = candidatoRepository.findByDni(dni)
                .orElseThrow(CandidatoNotFoundException::new);

        candidatoRepository.delete(candidato);
        return candidato.toResponse(candidato);
    }

    public CandidatoResponse modificar(CandidatoDTO candidatoDTO, String dni) {

        Candidato candidato = candidatoRepository.findByDni(dni)
                .orElseThrow(CandidatoNotFoundException::new);
        Candidato candidatoModificado = construirObjeto(candidatoDTO);

        candidatoRepository.delete(candidato);
        candidatoRepository.save(candidatoModificado);
        return candidatoModificado.toResponse(candidatoModificado);
    }

    public CandidatoResponse mostrar(String dni) {

        Candidato candidato = candidatoRepository.findByDni(dni)
                .orElseThrow(CandidatoNotFoundException::new);

        return candidato.toResponse(candidato);
    }
}
