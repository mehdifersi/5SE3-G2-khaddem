package tn.esprit.spring.khaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.entities.Equipe;

@Repository
public interface EquipeRepository  extends JpaRepository<Equipe, Integer> {
    

}
