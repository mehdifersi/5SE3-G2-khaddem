package tn.esprit.spring.khaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.khaddem.entities.Equipe;

import java.util.List;

public interface EquipRepository extends JpaRepository<Equipe,Integer> {


}
