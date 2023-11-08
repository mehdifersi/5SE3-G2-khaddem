package tn.esprit.spring.khaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Departement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepartement;
    private String nomDepart;
    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Etudiant> etudiants;

}
