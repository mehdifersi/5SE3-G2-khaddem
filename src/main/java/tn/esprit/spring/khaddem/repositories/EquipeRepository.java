package tn.esprit.spring.khaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Niveau;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface EquipeRepository extends JpaRepository<Equipe,Integer> {

 Equipe findByNomEquipe(String nom);
 ArrayList<Equipe> findByNiveau(Niveau niveau);

}
