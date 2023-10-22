package tn.esprit.spring.khaddem.services;

import tn.esprit.spring.khaddem.entities.Contrat;

import java.util.Date;
import java.util.List;

public interface IContratService {
    List<Contrat> retrieveAllContrats();
    Contrat updateContrat(Contrat ce);
    Contrat retrieveContrat(Integer idContrat);
    void removeContrat(Integer idContrat);
    Contrat addContrat(Contrat c);

    Contrat addAndAffectContratToEtudiant (Contrat ce, String nomE ,String prenomE );

    public 	Integer nbContratsValides(Date startDate, Date endDate);


    public float getChiffreAffaireEntreDeuxDates(Date startDate, Date endDate);

    public void retrieveAndUpdateStatusContrat();
}
