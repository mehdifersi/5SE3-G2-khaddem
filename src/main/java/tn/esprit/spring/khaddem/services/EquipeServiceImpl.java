package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class EquipeServiceImpl implements IEquipeService{

    EquipeRepository equipeRepository;

    ContratRepository contratRepository;

    @Override
    public List<Equipe> retrieveAllEquipes() {
        return equipeRepository.findAll();
    }

    @Transactional
    public Equipe addEquipe(Equipe e) {

        equipeRepository.save(e);
        return e;
    }

    @Override
    public Equipe updateEquipe(Equipe e) {
        Equipe equipe = equipeRepository.findById(e.getIdEquipe()).orElse(null);
        if(equipe!=null){
            equipe.setNomEquipe(e.getNomEquipe());
            equipe.setDetailEquipe(e.getDetailEquipe());
            equipe.setNiveau(e.getNiveau());
            return equipeRepository.save(equipe);
        }
        return null;
    }

    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        Optional<Equipe> equipeOptional = equipeRepository.findById(idEquipe);

        if (equipeOptional.isPresent()) {
            return equipeOptional.get();
        } else {
            throw new EntityNotFoundException("Equipe with id " + idEquipe + " not found");
        }
    }



    @Override
    public void evoluerEquipes() {
        log.info("debut methode evoluerEquipes");

        List<Equipe> equipes = equipeRepository.findAll();
        log.debug("nombre equipes : " + equipes.size());

        for (Equipe equipe : equipes) {
            if (shouldCheckEquipe(equipe)) {
                int nbEtudiantsAvecContratsActifs = countActiveEtudiants(equipe);

                if (nbEtudiantsAvecContratsActifs >= 3) {
                    upgradeNiveau(equipe);
                }
            }
        }

        log.info("fin methode evoluerEquipes");
    }

    private boolean shouldCheckEquipe(Equipe equipe) {
        return equipe.getEtudiants() != null && equipe.getEtudiants().isEmpty()
                && (equipe.getNiveau() == Niveau.JUNIOR || equipe.getNiveau() == Niveau.SENIOR);
    }

    private int countActiveEtudiants(Equipe equipe) {
        int nbEtudiantsAvecContratsActifs = 0;

        for (Etudiant etudiant : equipe.getEtudiants()) {
            List<Contrat> contrats = contratRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant());

            for (Contrat contrat : contrats) {
                if (isContratActive(contrat)) {
                    nbEtudiantsAvecContratsActifs++;
                    if (nbEtudiantsAvecContratsActifs >= 3) {
                        return nbEtudiantsAvecContratsActifs;
                    }
                }
            }
        }

        return nbEtudiantsAvecContratsActifs;
    }

    private boolean isContratActive(Contrat contrat) {
        if (contrat.getArchived() == null || !contrat.getArchived()) {
            long differenceInTime = contrat.getDateFinContrat().getTime() - contrat.getDateDebutContrat().getTime();
            long differenceInYears = differenceInTime / (1000L * 60 * 60 * 24 * 365);
            return differenceInYears > 1;
        }
        return false;
    }

    private void upgradeNiveau(Equipe equipe) {
        Niveau currentNiveau = equipe.getNiveau();
        Niveau newNiveau = (currentNiveau == Niveau.JUNIOR) ? Niveau.SENIOR : Niveau.EXPERT;

        log.info("mise à jour du niveau de l'équipe " + equipe.getNomEquipe() +
                " du niveau " + currentNiveau + " vers le niveau supérieur " + newNiveau);

        equipe.setNiveau(newNiveau);
        equipeRepository.save(equipe);
    }


}
