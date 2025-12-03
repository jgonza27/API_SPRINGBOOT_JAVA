package jesus.rest.dto;

import javax.validation.constraints.*;

public record PacienteDTO(
        Long id, 
        @NotBlank @Size(max = 60) String nombre,
        @Size(max = 255) String antecedentes) {


}