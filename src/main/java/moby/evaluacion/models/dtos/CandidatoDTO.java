package moby.evaluacion.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CandidatoDTO {

    private String nombre;

    private String apellido;

    private String tipoDni;

    private String numeroDni;

    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate fechaNacimiento;

}
