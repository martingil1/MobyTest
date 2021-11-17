package moby.evaluacion.models;

import moby.evaluacion.models.responses.TecnologiaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tecnologias")
public class Tecnologia {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idTecnologia;

    private String nombre;
    @Column(name = "version_tec")
    private String version;

    public TecnologiaResponse toResponse(Tecnologia tecnologia){

        return TecnologiaResponse.builder()
                .nombre(tecnologia.getNombre())
                .version(tecnologia.getVersion())
                .build();
    }

}
