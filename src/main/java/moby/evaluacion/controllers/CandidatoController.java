package moby.evaluacion.controllers;

import moby.evaluacion.models.dtos.CandidatoDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.services.CandidatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/candidato")
@RestController
public class CandidatoController {

    @Autowired
    CandidatoService candidatoService;

    @PostMapping("/cargar-candidato")
    public ResponseEntity<CandidatoResponse> cargarCandidato(@RequestBody CandidatoDTO candidatoDTO) {

        CandidatoResponse candidatoResponse = candidatoService.crear(candidatoDTO);
        return ResponseEntity.ok(candidatoResponse);
    }

    @GetMapping("/mostrar-candidato/{dni}")
    public ResponseEntity<CandidatoResponse> verCandidato(@PathVariable(value = "dni") String dni) {

        CandidatoResponse candidatoResponse = candidatoService.mostrar(dni);
        return ResponseEntity.ok(candidatoResponse);
    }

    @PostMapping("modificar-candidato/{dni}")
    public ResponseEntity<CandidatoResponse> modificarCandidato(@PathVariable(value = "dni") String dni, @RequestBody CandidatoDTO candidatoDTO) {

        CandidatoResponse candidatoResponse = candidatoService.modificar(candidatoDTO,dni);
        return ResponseEntity.ok(candidatoResponse);
    }

    @DeleteMapping("eliminar-candidato/{dni}")
    public ResponseEntity eliminarCandidato(@PathVariable(value = "dni") String dni){

        CandidatoResponse candidatoResponse = candidatoService.eliminar(dni);
        return ResponseEntity.ok("Candidato "+ candidatoResponse.getNombre() + " " + candidatoResponse.getApellido()+" eliminado");
    }
}
