package tn.esprit.spring.khaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.khaddem.entities.Equipe;
import tn.esprit.spring.khaddem.services.IEquipeService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/equipe")
public class EquipeRestController {
    IEquipeService equipeService;
    // http://localhost:8089/Kaddem/equipe/retrieve-all-equipes
    @GetMapping("/retrieve-all-equipes")
    @ResponseBody
    public List<Equipe> getEquipes() {
        List<Equipe> listEquipes = equipeService.retrieveAllEquipes();
        return listEquipes;
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
    public Equipe addEquipe(@RequestBody Equipe e) {
        Equipe equipe = equipeService.addEquipe(e);
        return equipe;
    }

    // http://localhost:8089/Kaddem/equipe/update-equipe
    @PutMapping("/update-equipe")
    @ResponseBody
    public Equipe updateEtudiant(@RequestBody Equipe e) {
        Equipe equipe= equipeService.updateEquipe(e);
        return equipe;
    }

   // @Scheduled(cron="0 0 13 * * *")
    @Scheduled(cron="* * 13 * * *")
    @PutMapping("/faireEvoluerEquipes")
    public void faireEvoluerEquipes() {
        equipeService.evoluerEquipes() ;
    }

}
