package jesus.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank @Size(min = 3, max = 50) String username,
        @NotBlank @Size(min = 6) String password,
        @NotBlank String role // Por ejemplo: "ROLE_ADMIN" o "ROLE_USER"
) {
}