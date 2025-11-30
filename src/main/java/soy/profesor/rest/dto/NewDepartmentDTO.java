package soy.profesor.rest.dto;

import javax.validation.constraints.*;

public record NewDepartmentDTO(
                @NotBlank @Size(max = 60) String name,
                @Size(max = 20) String phone,
                @NotBlank @Email String email) {
}