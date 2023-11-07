package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.Departement;

import java.util.List;

public interface IDepartementService {
    List<Departement> retrieveAllDepartements();
    Departement addDepartement(Departement d);
    Departement updateDepartement(Departement d);
    Departement retrieveDepartement(Integer idDepart);

    void removeDepartement(Integer idDept);
    List<Departement> retrieveDepartementsByUniversite(Integer idUniversite);



}
