package moby.evaluacion.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidatoResponse {

    private String nombre;

    private String apellido;

    private String experiencia;

}
