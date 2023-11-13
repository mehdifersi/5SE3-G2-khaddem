package tn.esprit.spring.khaddem;

import lombok.var;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
import tn.esprit.spring.khaddem.services.ContratServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ContratServiceImplMockTest {
    @InjectMocks
    private ContratServiceImpl contratService;
    @Mock
    private ContratRepository contratRepository;
    @Mock
    private EtudiantRepository etudiantRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testAddContract() {
        Contrat contrat = new Contrat();
        Mockito.when(contratRepository.save(contrat)).thenReturn(contrat);

        Contrat addedContrat = contratService.addContrat(contrat);

        Assertions.assertEquals(contrat, addedContrat);
    }
    @Test
    void testRetrieveContract() {
        int contratId = 1;
        Contrat contrat = new Contrat();
        Mockito.when(contratRepository.findById(contratId)).thenReturn(Optional.of(contrat));

        Contrat retrievedContrat = contratService.retrieveContrat(contratId);

        Assertions.assertEquals(contrat, retrievedContrat);
    }
    @Test
    void testRetrieveAllContracts() {
        List<Contrat> contracts = new ArrayList<>();
        Mockito.when(contratRepository.findAll()).thenReturn(contracts);

        List<Contrat> retrievedContracts = contratService.retrieveAllContrats();

        Assertions.assertEquals(contracts, retrievedContracts);
    }
    @Test
    void testRemoveContract() {
        int contratId = 1;
        Mockito.doNothing().when(contratRepository).deleteById(contratId);

        contratService.removeContrat(contratId);

        Mockito.verify(contratRepository, Mockito.times(1)).deleteById(contratId);
    }
    @Test
     void testNbContratsValides() {
        Date startDate = Date.valueOf("2023-03-04");
        Date endDate = Date.valueOf("2024-03-04");
        int expectedCount = 5;
        Mockito.when(contratRepository.getnbContratsValides(startDate, endDate)).thenReturn(expectedCount);
        int result = contratService.nbContratsValides(startDate, endDate);
        Mockito.verify(contratRepository).getnbContratsValides(startDate, endDate);
        Assertions.assertEquals(expectedCount, result);
    }

}
