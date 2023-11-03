package tn.esprit.spring.khaddem.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tn.esprit.spring.khaddem.entities.Etudiant;

import javax.persistence.OneToMany;
import java.util.List;

@Data

public class DepartementDto {
    private Integer idDepartement;
    private String nomDepart;
    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Etudiant> etudiants;
}
