package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EquipeServiceImplTest {

    @Test
    void retrieveAllEquipes() {
        // Create an instance of EquipeServiceImpl and mock dependencies
        EquipeRepository equipeRepository = Mockito.mock(EquipeRepository.class);
        EquipeServiceImpl equipeService = new EquipeServiceImpl(equipeRepository, null);

        // Mock the behavior of equipeRepository.findAll()
        List<Equipe> mockEquipes = new ArrayList<>();
        mockEquipes.add(new Equipe());
        when(equipeRepository.findAll()).thenReturn(mockEquipes);

        // Perform the test
        List<Equipe> equipes = equipeService.retrieveAllEquipes();

        // Assert that the returned list is not empty
        assertFalse(equipes.isEmpty());
        // You can add more specific assertions based on your test scenario
    }

//    @Test
//    void addEquipe() {
//        // Create an instance of EquipeServiceImpl and mock dependencies
//        EquipeRepository equipeRepository = Mockito.mock(EquipeRepository.class);
//        EquipeServiceImpl equipeService = new EquipeServiceImpl(equipeRepository, null);
//
//        // Create a mock equipe to add
//        Equipe mockEquipe = new Equipe(/* Initialize with necessary data */);
//
//        // Mock the behavior of equipeRepository.save()
//        when(equipeRepository.save(mockEquipe)).thenReturn(mockEquipe);
//
//        // Perform the test
//        Equipe addedEquipe = equipeService.addEquipe(mockEquipe);
//
//        // Assert that the returned equipe is the same as the one added
//        assertEquals(mockEquipe, addedEquipe);
//        // Verify that the save method was called
//        Mockito.verify(equipeRepository).save(mockEquipe);
//    }

//    @Test
//    void updateEquipe() {
//        // Create an instance of EquipeServiceImpl and mock dependencies
//        EquipeRepository equipeRepository = Mockito.mock(EquipeRepository.class);
//        EquipeServiceImpl equipeService = new EquipeServiceImpl(equipeRepository, null);
//
//        // Create a mock equipe to update
//        Equipe mockEquipe = new Equipe(/* Initialize with necessary data */);
//
//        // Mock the behavior of equipeRepository.save()
//        when(equipeRepository.save(mockEquipe)).thenReturn(mockEquipe);
//
//        // Perform the test
//        Equipe updatedEquipe = equipeService.updateEquipe(mockEquipe);
//
//        // Assert that the returned equipe is the same as the one updated
//        assertEquals(mockEquipe, updatedEquipe);
//        // Verify that the save method was called
//        Mockito.verify(equipeRepository).save(mockEquipe);
//    }

    @Test
    void retrieveEquipe() {
        // Create an instance of EquipeServiceImpl and mock dependencies
        EquipeRepository equipeRepository = Mockito.mock(EquipeRepository.class);
        EquipeServiceImpl equipeService = new EquipeServiceImpl(equipeRepository, null);

        // Create a mock equipe to retrieve
        Equipe mockEquipe = new Equipe(/* Initialize with necessary data */);

        // Mock the behavior of equipeRepository.findById()
        when(equipeRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(mockEquipe));

        // Perform the test
        Equipe retrievedEquipe = equipeService.retrieveEquipe(1);

        // Assert that the retrieved equipe is the same as the mock equipe
        assertEquals(mockEquipe, retrievedEquipe);
    }

//    @Test
//  void evoluerEquipes() {
//        // Create an instance of EquipeServiceImpl and mock dependencies
//        EquipeRepository equipeRepository = Mockito.mock(EquipeRepository.class);
//        ContratRepository contratRepository = Mockito.mock(ContratRepository.class);
//        EquipeServiceImpl equipeService = new EquipeServiceImpl(equipeRepository, contratRepository);
//
//        // Create a mock equipe with necessary data
//        Equipe mockEquipe = new Equipe();
//        mockEquipe.setNiveau(Niveau.JUNIOR);
//
//        // Create a list of mock etudiants with contrat data
//        List<Etudiant> etudiants = new ArrayList<>();
//        Etudiant etudiant1 = new Etudiant();
//        Etudiant etudiant2 = new Etudiant();
//        Etudiant etudiant3 = new Etudiant();
//        etudiants.add(etudiant1);
//        etudiants.add(etudiant2);
//        etudiants.add(etudiant3);
//
//        // Mock the behavior of equipeRepository.findAll()
//        when(equipeRepository.findAll()).thenReturn(Collections.singletonList(mockEquipe));
//
//        // Mock the behavior of contratRepository.findByEtudiantIdEtudiant()
//        when(contratRepository.findByEtudiantIdEtudiant(any())).thenReturn(new ArrayList<>());
//
//        // Perform the test
//        equipeService.evoluerEquipes();
//
//        // Assert that the niveau of the equipe has been updated correctly
//        assertEquals(Niveau.SENIOR, mockEquipe.getNiveau(), "Niveau of equipe should be updated to SENIOR.");
//    }

}
