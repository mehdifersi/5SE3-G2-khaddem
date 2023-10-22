package tn.esprit.spring.khaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.khaddem.entities.DetailEquipe;

@Repository
public interface DetailEquipeRepository extends JpaRepository<DetailEquipe, Integer> {
}
