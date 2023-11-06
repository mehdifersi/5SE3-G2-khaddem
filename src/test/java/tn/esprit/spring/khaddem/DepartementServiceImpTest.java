package tn.esprit.spring.khaddem;



import lombok.var;
import org.junit.Before;
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
import tn.esprit.spring.khaddem.dto.DepartementDto;
import tn.esprit.spring.khaddem.dto.DtoConverter;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;
import tn.esprit.spring.khaddem.services.DepartementServiceImpl;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class DepartementServiceImpTest {




    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @Mock
    private UniversiteRepository universiteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testRetrieveAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "Dept 1", new ArrayList<>()));

        Mockito.when(departementRepository.findAll()).thenReturn(departements);

        List<Departement> retrievedDepartements = departementService.retrieveAllDepartements();
        Assertions.assertEquals(departements, retrievedDepartements);

    }

    @Test
     void testRetrieveDepartement() {

        Departement departement = new Departement();
        departement.setIdDepartement(1);
        departement.setNomDepart("Dept 1");

        Mockito.when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        Departement retrievedDepartement = departementService.retrieveDepartement(1);

        Assertions.assertEquals(departement, retrievedDepartement);
    }

    @Test
    void testAddDepartment() {
        Departement departmentToAdd = new Departement();
        departmentToAdd.setNomDepart("Sample Department");
        when(departementRepository.save(departmentToAdd)).thenReturn(departmentToAdd);
        Departement addedDepartment = departementService.addDepartement(departmentToAdd);
        Assertions.assertEquals(departmentToAdd, addedDepartment);
    }


    @Test
    void testUpdateDepartement() {
        Departement sampleDepartement = new Departement();
        sampleDepartement.setIdDepartement(1);
        sampleDepartement.setNomDepart("Sample Department");

        Mockito.when(departementRepository.findById(1)).thenReturn(Optional.of(sampleDepartement));


        Departement updatedDepartement = new Departement();
        updatedDepartement.setIdDepartement(1);
        updatedDepartement.setNomDepart("Updated Department");


        Departement result = departementService.updateDepartement(updatedDepartement);

        assertEquals(sampleDepartement, result);

    }


    @Test
     void testRemoveDepartement() {
        Departement department = new Departement();
        department.setIdDepartement(1);

        Mockito.doNothing().when(departementRepository).deleteById(1);
        departementService.removeDepartement(1);
        verify(departementRepository).deleteById(1);
    }



    @Test
    void testRetrieveDepartementsByUniversite() {
        List<Departement> departements = new ArrayList<>();
        departements.add(new Departement(1, "Dept 1", new ArrayList<>()));
        departements.add(new Departement(2, "Dept 2", new ArrayList<>()));
        Universite universite = new Universite(1,"uni1",departements);

        when(universiteRepository.findById(universite.getIdUniversite())).thenReturn(Optional.of(universite));

        List<Departement> retrievedDepartements = departementService.retrieveDepartementsByUniversite(universite.getIdUniversite());

        assertEquals(departements, retrievedDepartements);
    }


}
