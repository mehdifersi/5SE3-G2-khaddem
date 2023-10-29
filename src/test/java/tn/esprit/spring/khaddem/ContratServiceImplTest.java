package tn.esprit.spring.khaddem;

import io.swagger.v3.oas.models.info.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;
import tn.esprit.spring.khaddem.services.IContratService;

import java.util.List;

@SpringBootTest
 class ContratServiceImplTest {
    @Autowired
    ContratRepository cr;

    @Test
     void retrieveAllContratsTest(){
        List<Contrat> contrats=cr.findAll();
        Assertions.assertEquals(0,contrats.size());
    }
    @Test
     void addContratTest(){
    Contrat c= cr.save(new Contrat());
    Assertions.assertNotNull(c);
    }
    @Test
     void retrieveContratTest(){
        Contrat c= cr.findById(1).get();
        Assertions.assertNotNull(c);
    }
    @Test
     void updateContratTest(){
        Contrat c = cr.findById(1).get();
        Contrat updatedContrat = cr.save(c);
        Assertions.assertNotNull(updatedContrat);

    }
    @Test
     void removeContratTest(){
        cr.deleteById(1);
        Contrat c=null;
        try {
            c=cr.findById(1).get();
        }catch (Exception e){

        }
        Assertions.assertEquals(0,cr.findAll().size());
    }

}
