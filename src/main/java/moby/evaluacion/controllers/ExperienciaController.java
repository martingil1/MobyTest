package moby.evaluacion.controllers;

import moby.evaluacion.models.dtos.ExperienciaDTO;
import moby.evaluacion.models.responses.CandidatoResponse;
import moby.evaluacion.services.ExperienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/exp")
@RestController
public class ExperienciaController {

    @Autowired
    ExperienciaService experienciaService;

    @PostMapping("/cargar-experiencia")
    public ResponseEntity cargarExperiencia(@RequestBody ExperienciaDTO experienciaDTO) {

        experienciaService.cargarExp(experienciaDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("candidatos-tecnologia/{nombre-tec}/{version}")
    public ResponseEntity<List<CandidatoResponse>> candidatosPorTecnologia(@PathVariable(value = "nombre-tec") String nombre, @PathVariable(value = "version") String version) {

        List<CandidatoResponse> candidatosResponse = experienciaService.candidatosPorTecnologia(nombre,version);
        return ResponseEntity.ok(candidatosResponse);
    }

}
