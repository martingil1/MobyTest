package moby.evaluacion.repositories;

import moby.evaluacion.models.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato,Long> {

    Optional<Candidato> findByIdCandidato(Long id);

    Candidato getByIdCandidato(Long id);

    Optional<Candidato> findByDni(String dni);

    Boolean existsByDni(String dni);

}
