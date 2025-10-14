package fr.eni.tpbasket.controllers;

import fr.eni.tpbasket.bll.EquipeService;
import fr.eni.tpbasket.bo.Equipe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipeRestController {
    private EquipeService equipeService;

    public EquipeRestController( EquipeService equipeService){
        this.equipeService = equipeService;
    }

    @GetMapping("/equipes")
    public List<Equipe> findAllEquipes() {
        return equipeService.getEquipes();
    }

}
