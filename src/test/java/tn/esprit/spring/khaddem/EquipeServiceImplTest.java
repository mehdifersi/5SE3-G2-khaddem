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
    }

    @Test
    void retrieveAllEquipes() {
        List<Equipe> teams = new ArrayList<>();
        teams.add(new Equipe());

        Mockito.when(equipeRepository.findAll()).thenReturn(teams);

        List<Equipe> retrievedTeams = equipeService.retrieveAllEquipes();

        Assertions.assertEquals(1, retrievedTeams.size());
        Assertions.assertEquals(teams.get(0), retrievedTeams.get(0));
    }

    @Test
    void addEquipe() {
        Equipe team = new Equipe();

        Mockito.when(equipeRepository.save(Mockito.any())).thenReturn(team);

        Equipe addedTeam = equipeService.addEquipe(team);

        Assert.assertNotNull(addedTeam);
        Assertions.assertEquals(team, addedTeam);
    }

    @Test
    void updateEquipe() {
        Equipe team = new Equipe();
        team.setIdEquipe(1);
        team.setNomEquipe("Team A");
        team.setNiveau(Niveau.JUNIOR);

        Mockito.when(equipeRepository.findById(Mockito.any())).thenReturn(Optional.of(team));
        Mockito.when(equipeRepository.save(Mockito.any())).thenReturn(team);

        Equipe updatedTeam = new Equipe();
        updatedTeam.setIdEquipe(1);
        updatedTeam.setNomEquipe("Updated Team A");
        updatedTeam.setNiveau(Niveau.SENIOR);

        Equipe updated = equipeService.updateEquipe(updatedTeam);

        Assert.assertNotNull(updated);
        Assertions.assertEquals("Updated Team A", updated.getNomEquipe());
        Assertions.assertEquals(Niveau.SENIOR, updated.getNiveau());
    }

    @Test
    void retrieveEquipe() {
        Equipe team = new Equipe();
        team.setIdEquipe(1);

        Mockito.when(equipeRepository.findById(1)).thenReturn(Optional.of(team));

        Equipe retrievedTeam = equipeService.retrieveEquipe(1);

        Assert.assertNotNull(retrievedTeam);
        Assertions.assertEquals(1, retrievedTeam.getIdEquipe());
    }

}
