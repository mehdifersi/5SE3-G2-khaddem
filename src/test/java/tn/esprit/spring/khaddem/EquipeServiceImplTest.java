package tn.esprit.spring.khaddem;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private ContratRepository contratRepository;

    @BeforeEach
    void setUp() {
        // Initialize mock data or configuration if needed
    }

    @Test
    void retrieveAllEquipes() {
        // Create a sample list of teams for testing
        List<Equipe> teams = new ArrayList<>();
        teams.add(new Equipe());

        // Mock the behavior of the repository
        Mockito.when(equipeRepository.findAll()).thenReturn(teams);

        // Call the service method
        List<Equipe> retrievedTeams = equipeService.retrieveAllEquipes();

        // Assertions to verify the results
        Assertions.assertEquals(1, retrievedTeams.size());
        Assertions.assertEquals(teams.get(0), retrievedTeams.get(0));
    }

    @Test
    void addEquipe() {
        // Create a sample team for testing
        Equipe team = new Equipe();

        // Mock the behavior of the repository
        Mockito.when(equipeRepository.save(Mockito.any())).thenReturn(team);

        // Call the service method
        Equipe addedTeam = equipeService.addEquipe(team);

        // Assertions to verify the results
        Assert.assertNotNull(addedTeam);
        Assertions.assertEquals(team, addedTeam);
    }

    @Test
    void updateEquipe() {
        // Create a sample team for testing
        Equipe team = new Equipe();
        team.setIdEquipe(1);
        team.setNomEquipe("Team A");
        team.setNiveau(Niveau.JUNIOR);

        // Mock the behavior of the repository
        Mockito.when(equipeRepository.findById(Mockito.any())).thenReturn(Optional.of(team));
        Mockito.when(equipeRepository.save(Mockito.any())).thenReturn(team);

        // Create an updated team
        Equipe updatedTeam = new Equipe();
        updatedTeam.setIdEquipe(1);
        updatedTeam.setNomEquipe("Updated Team A");
        updatedTeam.setNiveau(Niveau.SENIOR);

        // Call the service method
        Equipe updated = equipeService.updateEquipe(updatedTeam);

        // Assertions to verify the results
        Assert.assertNotNull(updated);
        Assertions.assertEquals("Updated Team A", updated.getNomEquipe());
        Assertions.assertEquals(Niveau.SENIOR, updated.getNiveau());
    }

    @Test
    void retrieveEquipe() {
        // Create a sample team for testing
        Equipe team = new Equipe();
        team.setIdEquipe(1);

        // Mock the behavior of the repository
        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(team));

        // Call the service method
        Equipe retrievedTeam = equipeService.retrieveEquipe(1);

        // Assertions to verify the results
        Assert.assertNotNull(retrievedTeam);
        Assertions.assertEquals(1, retrievedTeam.getIdEquipe());
    }

}
