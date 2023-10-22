package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Contrat;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.entities.Etudiant;
import tn.esprit.spring.khaddem.entities.Niveau;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
        equipeRepository.save(e);
        return e;
    }

    @Override
    public Equipe retrieveEquipe(Integer idEquipe) {
        return   equipeRepository.findById(idEquipe).get();
    }

    public void evoluerEquipes(){
        log.info("debut methode evoluerEquipes");
        // t1 : recuperer date a linstant t1
        List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
        log.debug("nombre equipes : "+equipes.size());
        for (Equipe equipe : equipes) {
            if (equipe.getEtudiants()!=null && equipe.getEtudiants().size() > 0) {
                log.debug("vérification niveau équipe");
            if ((equipe.getNiveau().equals(Niveau.JUNIOR)) || (equipe.getNiveau().equals(Niveau.SENIOR))) {
                List<Etudiant> etudiants = equipe.getEtudiants();

                    Integer nbEtudiantsAvecContratsActifs = 0;
                    for (Etudiant etudiant : etudiants) {
                     log.debug("in for etudiants");
                      //  List<Contrat> contrats = etudiant.getContrats();
                        List<Contrat> contrats = contratRepository.findByEtudiantIdEtudiant(etudiant.getIdEtudiant())  ;
                        for (Contrat contrat : contrats) {
                            log.debug("in contrat");

                            Date dateSysteme = new Date();
                            long difference_In_Time = contrat.getDateFinContrat().getTime() -contrat.getDateDebutContrat().getTime() ;
                            long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
                            log.debug("difference_In_Years: "+difference_In_Years);
                            if ((contrat.getArchived() == null || contrat.getArchived() == false) && (difference_In_Years > 1)) {
                                //	contratsActifs.add(contrat);
                                nbEtudiantsAvecContratsActifs++;
                                break;
                            }
                            if (nbEtudiantsAvecContratsActifs >= 3) break;
                        }
// t2 : recuperer date a linstant t2 : te=t2-t1
                    }
                    log.info("nbEtudiantsAvecContratsActifs: " + nbEtudiantsAvecContratsActifs);
                    if (nbEtudiantsAvecContratsActifs >= 3) {
                        if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
                            log.info("mise a jour du niveau de l equipe " + equipe.getNomEquipe() +
                                    " du niveau " + equipe.getNiveau() + " vers le niveau supérieur SENIOR");
                            equipe.setNiveau(Niveau.SENIOR);
                            equipeRepository.save(equipe);
                            // t2 : recuperer date a linstant t2 : te=t2-t1
                            break;
                        }
                        if (equipe.getNiveau().equals(Niveau.SENIOR)) {
                            log.info("mise a jour du niveau de l equipe " + equipe.getNomEquipe() +
                                    " du niveau " + equipe.getNiveau() + " vers le niveau supérieur EXPERT");
                            equipe.setNiveau(Niveau.EXPERT);
                            equipeRepository.save(equipe);
                            // t2 : recuperer date a linstant t2 : te=t2-t1
                            break;
                        }
                    }
                }

            }
        }
        log.info("fin methode evoluerEquipes");
        // t2 : recuperer date a linstant t2 : te=t2-t1
    }

}
