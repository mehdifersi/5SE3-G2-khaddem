package tn.esprit.spring.khaddem;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@Slf4j
 class EquipeRepositoryTest {
    @Autowired
    private EquipeRepository equipeRepository;

    @AfterEach
    void cleanDb()
    {
        equipeRepository.deleteAll();
    }
    @Test
    void testFindByNomEquipe(){
        var equipe = Equipe.builder()
                .idEquipe(1)
                .niveau(Niveau.EXPERT)
                .nomEquipe("Equipe IT")
                .build();
        equipeRepository.save(equipe);
        var dbEquipe = equipeRepository.findByNomEquipe(equipe.getNomEquipe());
        if (dbEquipe!=null)
        {
            boolean exists = Objects.equals(dbEquipe.getIdEquipe(), equipe.getIdEquipe());
            assertThat(exists).isTrue();
        }
    }
    @Test
    void testFindByNiveau(){
        for (int i=0; i<10; i++){
            var equipe = Equipe.builder()
                    .niveau(Niveau.EXPERT)
                    .nomEquipe("Equipe IT")
                    .build();
            equipeRepository.save(equipe);
        }
        var dbEquipes = equipeRepository.findByNiveau(Niveau.EXPERT);
        assert dbEquipes != null;
        boolean exists = Objects.equals(dbEquipes.size(), 10);
        assertThat(exists).isTrue();

    }



}