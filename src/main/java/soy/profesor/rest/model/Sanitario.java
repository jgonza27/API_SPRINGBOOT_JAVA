package soy.profesor.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Sanitario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String especialidad;

    @ManyToMany
    @JoinTable(name = "sanitario_cita", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "sanitario_id"), // Columna que referencia a Sanitario
            inverseJoinColumns = @JoinColumn(name = "cita_id") // Columna que referencia a Cita
    )
    private List<Cita> citas;
}