package moby.evaluacion.controllers;

import moby.evaluacion.models.dtos.CandidatoDTO;
import moby.evaluacion.models.dtos.TecnologiaDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.models.responses.TecnologiaResponse;
import moby.evaluacion.services.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/tecnologia")
@RestController
public class TecnologiaController {

    @Autowired
    TecnologiaService tecnologiaService;

    @PostMapping("/cargar-tecnologia")
    public ResponseEntity<TecnologiaResponse> cargarTecnologia(@RequestBody TecnologiaDTO tecnologiaDTO) {

        TecnologiaResponse tecnologiaResponse = tecnologiaService.crear(tecnologiaDTO);
        return ResponseEntity.ok(tecnologiaResponse);
    }

    @GetMapping("/mostrar-tecnologia/{nombre}/{version}")
    public ResponseEntity<TecnologiaResponse> verTecnologia(@PathVariable(value = "nombre") String nombre, @PathVariable(value = "version") String version) {

        TecnologiaResponse tecnologiaResponse = tecnologiaService.mostrar(nombre,version);
        return ResponseEntity.ok(tecnologiaResponse);
    }

    @PostMapping("modificar-tecnologia/{nombre}/{version}")
    public ResponseEntity<TecnologiaResponse> modificarTecnologia(@PathVariable(value = "nombre") String nombre, @PathVariable(value = "version") String version, @RequestBody TecnologiaDTO tecnologiaDTO) {

        TecnologiaResponse tecnologiaResponse = tecnologiaService.modificar(tecnologiaDTO,nombre,version);
        return ResponseEntity.ok(tecnologiaResponse);
    }

    @DeleteMapping("eliminar-tecnologia/{id}")
    public ResponseEntity eliminarTecnologia(@PathVariable(value = "id") Long id){

        TecnologiaResponse tecnologiaResponse = tecnologiaService.eliminar(id);
        return ResponseEntity.ok("Tecnologia "+ tecnologiaResponse.getNombre() + " eliminada");
    }
}
