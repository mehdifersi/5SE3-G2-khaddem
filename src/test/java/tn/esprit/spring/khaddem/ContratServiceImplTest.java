package tn.esprit.spring.khaddem;

import io.swagger.v3.oas.models.info.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Specialite;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;
import tn.esprit.spring.khaddem.services.IContratService;

import javax.validation.constraints.Null;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
 class ContratServiceImplTest {
    @Autowired
    ContratServiceImpl contratService;
    @Autowired
    ContratRepository contratRepository;
   @Autowired
   EtudiantRepository etudiantRepository;

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse date.", e);
        }
    }

    @Test
     void retrieveAllContratsTest(){
        List<Contrat> contrats = contratService.retrieveAllContrats();
        List<Contrat> allContratsInRepository = contratRepository.findAll();

        Assertions.assertEquals(allContratsInRepository.size(), contrats.size());
        for (int i = 0; i < allContratsInRepository.size(); i++) {
            Contrat expected = allContratsInRepository.get(i);
            Contrat actual = contrats.get(i);
            Assertions.assertEquals(expected, actual);
        }
    }
    @Test
     void addContratTest(){
        Contrat contrat = new Contrat();
        Contrat addedContrat = contratService.addContrat(contrat);
        Assertions.assertNotNull(addedContrat);
        contratRepository.delete(addedContrat);
        Assertions.assertNull(contratRepository.findById(addedContrat.getIdContrat()).orElse(null));
    }
    @Test
     void retrieveContratTest(){
        Contrat contrat = new Contrat();
        contratRepository.save(contrat);
        Contrat c= contratService.retrieveContrat(contrat.getIdContrat());
        Assertions.assertEquals(contrat.getIdContrat(), c.getIdContrat());
        contratRepository.delete(contrat);
        Assertions.assertNull(contratRepository.findById(contrat.getIdContrat()).orElse(null));
    }
    @Test
     void updateContratTest(){
        Contrat sampleContrat = new Contrat();
        contratRepository.save(sampleContrat);
        sampleContrat.setMontantContrat(500);
        Contrat updatedContrat = contratService.updateContrat(sampleContrat);
        Contrat retrievedContrat = contratRepository.findById(sampleContrat.getIdContrat()).orElse(null);
        Assertions.assertEquals(sampleContrat.getIdContrat(), updatedContrat.getIdContrat());
        Assertions.assertEquals(500, updatedContrat.getMontantContrat());
        Assertions.assertEquals(sampleContrat.getIdContrat(), retrievedContrat.getIdContrat());
        Assertions.assertEquals(500, retrievedContrat.getMontantContrat());
    }
    @Test
     void removeContratTest(){
        Contrat sampleContrat = new Contrat();
        contratRepository.save(sampleContrat);
        contratService.removeContrat(sampleContrat.getIdContrat());
        Assertions.assertNull(contratRepository.findById(sampleContrat.getIdContrat()).orElse(null));
    }
   @Test
    void testAddAndAffectContratToEtudiant() {
      Etudiant etudiant = new Etudiant();
      etudiant.setNomE("John");
      etudiant.setPrenomE("Doe");
      etudiantRepository.save(etudiant);
      Contrat contrat = new Contrat();
      Contrat addedContrat = contratService.addAndAffectContratToEtudiant(contrat, "John", "Doe");
      Assertions.assertEquals(contrat, addedContrat);
      Assertions.assertNotNull(contrat.getEtudiant());
       contratRepository.delete(addedContrat);
       etudiantRepository.delete(etudiant);
       Assertions.assertNull(contratRepository.findById(addedContrat.getIdContrat()).orElse(null));
       Assertions.assertNull(etudiantRepository.findById(etudiant.getIdEtudiant()).orElse(null));
    }
    @Test
        void testNbContratsValides() {
        // Create sample contracts with specific start and end dates
        Contrat contrat1 = new Contrat();
        contrat1.setDateDebutContrat(parseDate("2023-01-01"));
        contrat1.setDateFinContrat(parseDate("2023-01-15"));
        contratRepository.save(contrat1);
        Contrat contrat2 = new Contrat();
        contrat2.setDateDebutContrat(parseDate("2023-01-16"));
        contrat2.setDateFinContrat(parseDate("2023-01-31"));
        contratRepository.save(contrat2);
        Date startDate = parseDate("2023-01-01");
        Date endDate = parseDate("2023-01-31");
        Integer validContractCount = contratService.nbContratsValides(startDate, endDate);
        Assertions.assertEquals(2, validContractCount);
        contratRepository.delete(contrat1);
        contratRepository.delete(contrat2);
    }
    @Test
     void testRetrieveAndUpdateStatusContrat() {
        Contrat sampleContrat = new Contrat();
        sampleContrat.setDateDebutContrat(parseDate("2023-01-01"));
        sampleContrat.setDateFinContrat(parseDate("2023-01-15"));
        sampleContrat.setArchived(true);
        contratRepository.save(sampleContrat);
        contratService.retrieveAndUpdateStatusContrat();
        Contrat updatedContrat = contratRepository.findById(sampleContrat.getIdContrat()).orElse(null);
        Assertions.assertTrue(updatedContrat.getArchived());
        contratRepository.delete(updatedContrat);
    }
}
