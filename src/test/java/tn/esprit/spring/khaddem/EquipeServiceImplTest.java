package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
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


}
