package tn.esprit.spring.khaddem.dto;


import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.khaddem.entities.Niveau;

@Getter
@Setter
public class EquipeDTO {
    private String nomEquipe;
    private Niveau niveau;

}
