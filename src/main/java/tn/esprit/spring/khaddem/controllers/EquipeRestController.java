package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.dto.EquipeDTO;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.IEquipeService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
    @Autowired
    EquipeRepository equipeRepository;
    IEquipeService equipeService;
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {
        return equipeService.retrieveAllEquipes();
    }


    // http://localhost:8089/Kaddem/equipe/retrieve-equipe/8
    @GetMapping("/retrieve-equipe/{equipe-id}")
    @ResponseBody
    public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
        return equipeService.retrieveEquipe(equipeId);
    }

    // http://localhost:8089/Kaddem/equipe/add-equipe
    /* cette méthode permet d'ajouter une équipe avec son détail*/
    @PostMapping("/add-equipe")
    @ResponseBody
    public Equipe addEquipe(@RequestBody EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();
        equipe.setNomEquipe(equipeDTO.getNomEquipe());
        equipe.setNiveau(equipeDTO.getNiveau());
        return equipeService.addEquipe(equipe);
    }

    @PutMapping("/update-equipe/{id}")
    @ResponseBody
    public Equipe updateEtudiant(@PathVariable Integer id,@RequestBody EquipeDTO equipeDTO) {
        Equipe existingEquipe = equipeRepository.findById(id).orElse(null);
        if (existingEquipe!=null){
            existingEquipe.setNomEquipe(equipeDTO.getNomEquipe());
            existingEquipe.setNiveau(equipeDTO.getNiveau());
            return  equipeService.updateEquipe(existingEquipe);
        }
        return new Equipe();
    }

   // @Scheduled(cron="0 0 13 * * *")
    @Scheduled(cron="* * 13 * * *")
    @PutMapping("/faireEvoluerEquipes")
    public void faireEvoluerEquipes() {
        equipeService.evoluerEquipes() ;
    }

}
