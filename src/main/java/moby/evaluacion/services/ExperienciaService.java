package moby.evaluacion.services;

import moby.evaluacion.exceptions.TecnologiaNotFoundException;
import moby.evaluacion.models.dtos.ExperienciaDTO;
import moby.evaluacion.models.Experiencia;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.models.Tecnologia;
import moby.evaluacion.repositories.CandidatoRepository;
import moby.evaluacion.repositories.ExperienciaRepository;
import moby.evaluacion.repositories.TecnologiaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExperienciaService {

    @Autowired
    ExperienciaRepository experienciaRepository;

    @Autowired
    CandidatoRepository candidatoRepository;

    @Autowired
    TecnologiaRepository tecnologiaRepository;

    public void cargarExp(ExperienciaDTO experienciaDTO) {

        experienciaRepository.save(Experiencia.builder()
                .experiencia(experienciaDTO.getExperiencia())
                .candidato(candidatoRepository.findByIdCandidato(experienciaDTO.getCandidatoId())
                        .orElseThrow(NullPointerException::new))
                .tecnologia(tecnologiaRepository.findByIdTecnologia(experienciaDTO.getTecnologiaId())
                        .orElseThrow(NullPointerException::new))
                .build());

    }

    public List<CandidatoResponse> candidatosPorTecnologia(String nombre, String version) {

        Tecnologia tec = tecnologiaRepository.findByNombreAndVersion(nombre, version)
                .orElseThrow(TecnologiaNotFoundException::new);

        List<Experiencia> listExperiencia = experienciaRepository.candidatosPorTecnologia(tec.getIdTecnologia());

        if(listExperiencia.isEmpty()){
            log.info("Ningun candidato encontrado con esa tecnologia");
        }

        return listExperiencia.stream()
                .map(experiencia -> CandidatoResponse.builder()
                        .nombre(candidatoRepository.getByIdCandidato(experiencia.getCandidato().getIdCandidato()).getNombre())
                        .apellido(candidatoRepository.getByIdCandidato(experiencia.getCandidato().getIdCandidato()).getApellido())
                        .experiencia(experiencia.getExperiencia())
                        .build())
                .collect(Collectors.toList());
    }

}
