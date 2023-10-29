package tn.esprit.spring.khaddem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UniversiteServiceImplTest {
    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void retrieveAllUniversites() {
        List<Universite> expectedUniversites = new ArrayList<>();
        expectedUniversites.add(new Universite(1, "ESPRIT", new ArrayList<>()));
        expectedUniversites.add(new Universite(2, "TEKUP", new ArrayList<>()));
        when(universiteRepository.findAll()).thenReturn(expectedUniversites);
        List<Universite> actualUniversites = universiteService.retrieveAllUniversites();
        Assertions.assertEquals(expectedUniversites, actualUniversites);
    }

    @Test
    public void addUniversite() {
        Universite universiteToAdd = new Universite();
        universiteToAdd.setNomUniv("Sample Universite");
        when(universiteRepository.save((universiteToAdd))).thenReturn(universiteToAdd);
        Universite addedUniversite = universiteService.addUniversite(universiteToAdd);
        Assertions.assertEquals(universiteToAdd, addedUniversite);
    }
    @Test
    public void updateUniversite() {
        Universite universiteToUpdate = new Universite();
        universiteToUpdate.setIdUniversite(1);
        universiteToUpdate.setNomUniv("Updated Universite");
        when(universiteRepository.save((universiteToUpdate))).thenReturn(universiteToUpdate);
        Universite updatedUniversite = universiteService.updateUniversite(universiteToUpdate);
        Assertions.assertEquals(universiteToUpdate, updatedUniversite);
    }
    @Test
    public void retrieveUniversite() {
        Universite expectedUniversite = new Universite();
        expectedUniversite.setIdUniversite(1);
        when(universiteRepository.findById(1)).thenReturn(Optional.of(expectedUniversite));
        Universite retrievedUniversite = universiteService.retrieveUniversite(1);
        Assertions.assertEquals(expectedUniversite, retrievedUniversite);
    }


}
