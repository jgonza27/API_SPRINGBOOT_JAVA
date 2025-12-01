package soy.profesor.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora; // CORREGIDO: minúsculas

    private String sintomas; // CORREGIDO: minúsculas

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "sanitario_cita",
        joinColumns = @JoinColumn(name = "cita_id"),
        inverseJoinColumns = @JoinColumn(name = "sanitario_id")
    )
    private List<Sanitario> sanitarios;

    @PreRemove
    private void removeSanitariosFromCita() {
        if (this.sanitarios != null) {
            this.sanitarios.clear();
        }
    }
}