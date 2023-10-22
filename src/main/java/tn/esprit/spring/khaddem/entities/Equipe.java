package tn.esprit.spring.khaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipe  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer idEquipe;

    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @ManyToMany(mappedBy = "equipes",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
   // @JsonIgnore
    private List<Etudiant> etudiants;
    @OneToOne(cascade = CascadeType.ALL)
    private DetailEquipe detailEquipe;
}
