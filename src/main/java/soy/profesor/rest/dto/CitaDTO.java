package soy.profesor.rest.dto;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.*;

public record CitaDTO(
        Long id,
        @NotNull LocalDateTime fechaHora,
        @NotBlank @Size(max = 255) String sintomas,
        @NotNull Long pacienteId,
        List<Long> sanitariosIds // IDs de los sanitarios que atenderán la cita
) {
}