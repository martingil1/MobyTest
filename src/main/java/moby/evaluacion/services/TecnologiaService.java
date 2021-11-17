package moby.evaluacion.services;

import lombok.RequiredArgsConstructor;
import moby.evaluacion.exceptions.TecnologiaNotFoundException;
import moby.evaluacion.models.dtos.TecnologiaDTO;
import moby.evaluacion.models.responses.TecnologiaResponse;
import moby.evaluacion.models.Tecnologia;
import moby.evaluacion.repositories.TecnologiaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class TecnologiaService {

    private final TecnologiaRepository tecnologiaRepository;

    public TecnologiaResponse crear(TecnologiaDTO tecnologiaDTO) {

        Tecnologia tecnologia = tecnologiaRepository.save(Tecnologia.builder()
                .nombre(tecnologiaDTO.getNombre())
                .version(tecnologiaDTO.getVersion())
                .build());

        return tecnologia.toResponse(tecnologia);
    }

    public TecnologiaResponse eliminar(Long id) {

        Tecnologia tecnologia = tecnologiaRepository.getById(id);

        tecnologiaRepository.delete(tecnologia);
        return tecnologia.toResponse(tecnologia);
    }

    public TecnologiaResponse modificar(TecnologiaDTO tecnologiaDTO, String nombre, String version) {

        Tecnologia tecnologia = tecnologiaRepository.findByNombreAndVersion(nombre, version)
                .orElseThrow(TecnologiaNotFoundException::new);

        Tecnologia tecnologiaModificada = Tecnologia.builder()
                .nombre(tecnologiaDTO.getNombre())
                .version(tecnologiaDTO.getVersion())
                .build();

        tecnologiaRepository.delete(tecnologia);
        tecnologiaRepository.save(tecnologiaModificada);
        return tecnologiaModificada.toResponse(tecnologiaModificada);
    }

    public TecnologiaResponse mostrar(String nombre, String version) {

        Tecnologia tecnologia = tecnologiaRepository.findByNombreAndVersion(nombre, version)
                .orElseThrow(TecnologiaNotFoundException::new);;

        return tecnologia.toResponse(tecnologia);
    }
}
