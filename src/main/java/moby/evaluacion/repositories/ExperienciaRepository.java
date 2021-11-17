package moby.evaluacion.repositories;

import moby.evaluacion.models.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia,Long> {

    String query = "SELECT * FROM experiencia as e "+
            "JOIN candidatos as c on c.id = e.id_candidato "+
            "JOIN tecnologias as t on t.id = e.id_tec "+
            "WHERE t.id = ?1 ;";

    @Query(value = query, nativeQuery = true)
    List<Experiencia> candidatosPorTecnologia(Long id);
}
