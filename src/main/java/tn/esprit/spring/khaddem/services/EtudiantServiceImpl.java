package tn.esprit.spring.khaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.*;
import tn.esprit.spring.khaddem.repositories.ContratRepository;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.repositories.EtudiantRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService{
    @Autowired
    EtudiantRepository etudiantRepository;

    DepartementRepository departementRepository;

    ContratRepository contratRepository;

    EquipeRepository equipeRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addOrUpdateEtudiant(Etudiant e) {
        etudiantRepository.save(e);
        return e;
    }

    @Override
    public Etudiant retrieveEtudiant(Integer idEtudiant) {
        return etudiantRepository.findById(idEtudiant).orElse(null);
    }

    @Override
    public void removeEtudiant(Integer idEtudiant) {
     etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public void assignEtudiantToDepartement(Integer etudiantId, Integer departementId) {
        Etudiant e = etudiantRepository.findById(etudiantId).orElse(null);
        Departement d= departementRepository.findById(departementId).orElse(null);
        if (e!=null) {
            e.setDepartement(d);
            etudiantRepository.save(e);
        }
    }

    @Override
    public List<Etudiant> findByDepartementIdDepartement(Integer idDepartement) {
        return etudiantRepository.findByDepartementIdDepartement(idDepartement);
    }

    @Override
    public List<Etudiant> findByEquipesNiveau(Niveau niveau) {
        return etudiantRepository.findByEquipesNiveau(niveau);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialite(Specialite specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialite(specialite);
    }

    @Override
    public List<Etudiant> retrieveEtudiantsByContratSpecialiteSQL(String specialite) {
        return etudiantRepository.retrieveEtudiantsByContratSpecialiteSQL(specialite);
    }

    @Transactional
    public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe) {
        Contrat contrat = contratRepository.findById(idContrat).orElse(null);
        Equipe equipe=equipeRepository.findById(idEquipe).orElse(null);
        Etudiant etudiant= etudiantRepository.save(e);
        List<Equipe> equipesMisesAjour = new ArrayList<>();
        if (contrat!=null && equipe!=null){
            contrat.setEtudiant(etudiant);
            if(etudiant.getEquipes()!=null) {
                equipesMisesAjour=etudiant.getEquipes();
            }
            equipesMisesAjour.add(equipe);
            etudiant.setEquipes(equipesMisesAjour);
            return e;
        }
        return new Etudiant();
    }

    @Override
    public List<Etudiant> getEtudiantsByDepartement(Integer idDepartement) {
        Departement departement=departementRepository.findById(idDepartement).orElse(null);
        if (departement!=null){
            return departement.getEtudiants();
        }
        return new ArrayList<>();
    }


}
