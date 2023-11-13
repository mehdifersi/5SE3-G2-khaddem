package tn.esprit.spring.khaddem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tn.esprit.spring.khaddem.dto.DepartementDto;
import tn.esprit.spring.khaddem.dto.DtoConverter;
import tn.esprit.spring.khaddem.entities.Departement;
import tn.esprit.spring.khaddem.services.IDepartementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/departement")

public class DepartementRestController {
    @Autowired
    IDepartementService departementService;


    @Autowired
    DtoConverter dtoConverter;



    // http://localhost:8089/Kaddem/departement/retrieve-all-departements
    @GetMapping("/retrieve-all-departements")
    @ResponseBody
    public List<Departement> getDepartements() {
        return departementService.retrieveAllDepartements();

    }

    // http://localhost:8089/Kaddem/departement/retrieve-departement/8
    @GetMapping("/retrieve-departement/{departement-id}")
    @ResponseBody
    public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
        return departementService.retrieveDepartement(departementId);
    }

    // http://localhost:8089/Kaddem/departement/add-departement

    @PostMapping("/add-departement")
    @ResponseBody
    public DepartementDto addDepartement(@Valid @RequestBody DepartementDto departementDto) {
        Departement department = dtoConverter.convertDtoToEntity(departementDto, Departement.class);
        departementService.addDepartement(department);
        return dtoConverter.convertEntityToDto(department, DepartementDto.class);
    }
    // http://localhost:8089/Kaddem/departement/update-departement
    @PutMapping("/update-departement")
    @ResponseBody
    public DepartementDto updateDepartement(@Valid @RequestBody DepartementDto departementDto) {
        Departement department = dtoConverter.convertDtoToEntity(departementDto, Departement.class);
        departementService.updateDepartement(department);
        return dtoConverter.convertEntityToDto(department, DepartementDto.class);
    }



    // http://localhost:8089/Kaddem/departement/remove-departement/2
    @DeleteMapping("/remove-departement/{departement-id}")
    public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
        departementService.removeDepartement(departementId);
    }


    // http://localhost:8089/Kaddem/departement/retrieveDepartementsByUniversite/1
    @GetMapping("/retrieveDepartementsByUniversite/{idUniversite}")
    @ResponseBody
    public List<Departement> retrieveDepartementsByUniversite(@PathVariable("idUniversite") Integer idUniversite) {
        return departementService.retrieveDepartementsByUniversite(idUniversite);

    }


}
