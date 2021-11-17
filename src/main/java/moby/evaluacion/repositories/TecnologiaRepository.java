package moby.evaluacion.repositories;

import moby.evaluacion.models.Tecnologia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia,Long> {

    Optional<Tecnologia> findByNombreAndVersion(String nombre, String version);

    boolean existsByNombreAndVersion(String nombre, String version);

    Optional<Tecnologia> findByIdTecnologia(Long id);
}
