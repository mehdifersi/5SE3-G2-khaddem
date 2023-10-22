package tn.esprit.spring.khaddem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.khaddem.entities.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
