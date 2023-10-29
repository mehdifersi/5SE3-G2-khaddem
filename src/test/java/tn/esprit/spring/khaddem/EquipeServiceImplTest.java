package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.EquipeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

 class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;
    @Mock
    private ContratRepository contratRepository;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void retrieveAllEquipes() {
        List<Equipe> expectedEquipes = new ArrayList<>();
        expectedEquipes.add(new Equipe(1, "Equipe1", Niveau.JUNIOR, new ArrayList<>()));
        expectedEquipes.add(new Equipe(2, "Equipe2", Niveau.SENIOR, new ArrayList<>()));
        when(equipeRepository.findAll()).thenReturn(expectedEquipes);
        List<Equipe> actualEquipes = equipeService.retrieveAllEquipes();
        Assertions.assertEquals(expectedEquipes, actualEquipes);
    }

    @Test
    void addEquipe() {
        Equipe equipeToAdd = new Equipe();
        equipeToAdd.setNomEquipe("Sample Equipe");
        when(equipeRepository.save((equipeToAdd))).thenReturn(equipeToAdd);
        Equipe addedEquipe = equipeService.addEquipe(equipeToAdd);
        Assertions.assertEquals(equipeToAdd, addedEquipe);
    }
    @Test
    void updateEquipe() {
        Equipe equipeToUpdate = new Equipe();
        equipeToUpdate.setIdEquipe(1);
        equipeToUpdate.setNomEquipe("Updated Equipe");
        when(equipeRepository.save((equipeToUpdate))).thenReturn(equipeToUpdate);
        Equipe updatedEquipe = equipeService.updateEquipe(equipeToUpdate);
        Assertions.assertEquals(equipeToUpdate, updatedEquipe);
    }
    @Test
    void retrieveEquipe() {
        Equipe expectedEquipe = new Equipe();
        expectedEquipe.setIdEquipe(1);
        when(equipeRepository.findById(1)).thenReturn(Optional.of(expectedEquipe));
        Equipe retrievedEquipe = equipeService.retrieveEquipe(1);
        Assertions.assertEquals(expectedEquipe, retrievedEquipe);
    }

    @Test
    void evoluerEquipes() {
        List<Equipe> equipeList = new ArrayList<>();
        Equipe equipe1 = new Equipe();
        equipe1.setNiveau(Niveau.JUNIOR);
        equipe1.setEtudiants(new ArrayList<>());
        equipeList.add(equipe1);
        Equipe equipe2 = new Equipe();
        equipe2.setNiveau(Niveau.SENIOR);
        equipe2.setEtudiants(new ArrayList<>());
        equipeList.add(equipe2);
        when(equipeRepository.findAll()).thenReturn(equipeList);
        when(contratRepository.findByEtudiantIdEtudiant(anyInt())).thenReturn(new ArrayList<>());
        equipeService.evoluerEquipes();
    }
}
