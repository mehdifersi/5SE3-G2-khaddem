package tn.esprit.spring.khaddem.services;

import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;
        import tn.esprit.spring.khaddem.entities.*;
        import tn.esprit.spring.khaddem.repositories.ContratRepository;
        import tn.esprit.spring.khaddem.repositories.DepartementRepository;
        import tn.esprit.spring.khaddem.repositories.EquipeRepository;
        import tn.esprit.spring.khaddem.repositories.EtudiantRepository;
        import tn.esprit.spring.khaddem.services.EtudiantServiceImpl;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplTest {

    @Mock
    EtudiantRepository etudiantRepository;

    @Mock
    DepartementRepository departementRepository;

    @Mock
    ContratRepository contratRepository;

    @Mock
    EquipeRepository equipeRepository;

    @InjectMocks
    EtudiantServiceImpl etudiantService;

    @Test
    void testRetrieveAllEtudiants() {
        Etudiant etudiant1 = new Etudiant(1, "John", "Doe",  null, null, null);
        Etudiant etudiant2 = new Etudiant(2, "Jane", "Doe", null, null, null);

        when(etudiantRepository.findAll()).thenReturn(List.of(etudiant1, etudiant2));

        List<Etudiant> allEtudiants = etudiantService.retrieveAllEtudiants();

        assertEquals(2, allEtudiants.size());
    }

    // Ajoutez des tests similaires pour les autres méthodes de service.

    // Exemple pour testAddEtudiant
    @Test
    void testAddEtudiant() {
        Etudiant etudiant = new Etudiant(1, "John", "Doe",  null, null, null);
        when(etudiantRepository.save(etudiant)).thenReturn(etudiant);

        Etudiant addedEtudiant = etudiantService.addEtudiant(etudiant);

        assertEquals(etudiant, addedEtudiant);
    }

    // Exemple pour testRetrieveEtudiant
    @Test
    void testRetrieveEtudiant() {
        Etudiant etudiant = new Etudiant(1, "John", "Doe",  null, null, null);
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));

        Etudiant retrievedEtudiant = etudiantService.retrieveEtudiant(1);

        assertEquals(etudiant, retrievedEtudiant);
    }

    // Ajoutez des tests similaires pour les autres méthodes de service.
}
