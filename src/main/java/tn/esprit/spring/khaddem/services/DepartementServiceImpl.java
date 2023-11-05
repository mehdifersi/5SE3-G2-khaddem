package tn.esprit.spring.khaddem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.khaddem.entities.Departement;
import lombok.extern.slf4j.Slf4j;

import tn.esprit.spring.khaddem.entities.Universite;
import tn.esprit.spring.khaddem.repositories.DepartementRepository;
import tn.esprit.spring.khaddem.repositories.UniversiteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j

public class DepartementServiceImpl implements IDepartementService{
    @Autowired
    DepartementRepository departementRepository;
    @Autowired
    UniversiteRepository universiteRepository;





    @Override
    public List<Departement> retrieveAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement addDepartement(Departement d) {departementRepository.save(d);
        return d;
    }




    @Override
    public Departement updateDepartement(Departement updatedDepartement) {
        Departement existingDepartement = departementRepository.findById(updatedDepartement.getIdDepartement()).orElse(null);

        if (existingDepartement != null) {
            existingDepartement.setNomDepart(updatedDepartement.getNomDepart());

            departementRepository.save(existingDepartement);
        } else {
            log.info("Departement does not exist. Update operation failed.");
        }

        return existingDepartement;
    }


    @Override
    public Departement retrieveDepartement(Integer idDepart) {
        Optional<Departement> optionalDepartement = departementRepository.findById(idDepart);
        if (optionalDepartement.isPresent()) {
            return optionalDepartement.get();
        } else {
            log.info("Departement does not exist.");
            return null;
        }
    }

    @Override
    public void removeDepartement(Integer idDept) {
        departementRepository.deleteById(idDept);

    }

    @Override
    public List<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Optional<Universite> optionalUniversite = universiteRepository.findById(idUniversite);
        if (optionalUniversite.isPresent()) {
            Universite universite = optionalUniversite.get();
            return universite.getDepartements();
        } else {
            return Collections.emptyList();
        }
    }




}
