package soy.profesor.rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

// @Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Department {

    // Atributos
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    private String email;
}