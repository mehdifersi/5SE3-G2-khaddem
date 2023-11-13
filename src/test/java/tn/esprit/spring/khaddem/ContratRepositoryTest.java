package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Option;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class ContratRepositoryTest {
    @Autowired
    ContratRepository contratRepository;
    @Autowired
    EtudiantRepository etudiantRepository;
    @AfterEach
    void cleanDb(){
        contratRepository.deleteAll();
    }
    @Test
    void GetnbContratsValidesTest(){
        Contrat contrat1=new Contrat(1, Date.valueOf("2023-03-04"),Date.valueOf("2023-12-30"),null,true,null,null);
        Contrat contrat2=new Contrat(2, Date.valueOf("2023-06-07"),Date.valueOf("2023-12-30"),null,true,null,null);
        contratRepository.save(contrat1);
        contratRepository.save(contrat2);
        Date startDate = Date.valueOf("2023-01-01");
        Date endDate = Date.valueOf("2023-12-31");
        Integer result = contratRepository.getnbContratsValides(startDate, endDate);
        int expectedCount = 2;
        Assertions.assertEquals(expectedCount, result);
    }
    @Test
    void testFindByEtudiantIdEtudiant() {
        Etudiant etudiant = new Etudiant(1,"Dhia","Hamrouni", Option.GAMIX,null,null,new ArrayList<>());
        etudiantRepository.save(etudiant);
        Contrat contrat1=new Contrat(1, Date.valueOf("2023-03-04"),Date.valueOf("2023-12-30"),null,true,null,etudiant);
        Contrat contrat2=new Contrat(2, Date.valueOf("2023-06-07"),Date.valueOf("2023-12-30"),null,true,null,etudiant);
        contratRepository.save(contrat1);
        contratRepository.save(contrat2);
        List<Contrat> contratList = contratRepository.findByEtudiantIdEtudiant(1);
        Assertions.assertEquals(2, contratList.size());
    }


}
