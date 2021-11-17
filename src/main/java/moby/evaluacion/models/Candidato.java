package moby.evaluacion.models;

import moby.evaluacion.models.responses.CandidatoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidatos")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long idCandidato;

    private String nombre;

    private String apellido;

    @Column(name = "tipo_doc")
    private String tipoDoc;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nacimiento")
    private LocalDate fechaNacimiento;

    public CandidatoResponse toResponse(Candidato candidato){

        return CandidatoResponse.builder()
                .nombre(candidato.getNombre())
                .apellido(candidato.getApellido())
                .build();
    }

}
