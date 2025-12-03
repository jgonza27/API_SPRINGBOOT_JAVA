package jesus.rest.dto;

import javax.validation.constraints.NotBlank;

public record LoginUserDTO(
        @NotBlank String username,
        @NotBlank String password) {
}