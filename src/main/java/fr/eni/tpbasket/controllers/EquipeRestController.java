package fr.eni.tpbasket.controllers;

import fr.eni.tpbasket.bll.EquipeService;
import fr.eni.tpbasket.bll.JoueurService;
import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.dto.EquipeDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class EquipeRestController {
    private final JoueurService joueurService;
    private EquipeService equipeService;

    public EquipeRestController(EquipeService equipeService, JoueurService joueurService){
        this.equipeService = equipeService;
        this.joueurService = joueurService;
    }

    //US001
    @GetMapping("/equipes")
    public List<Equipe> findAllEquipes() {
        return equipeService.getEquipes();
    }

    //US002
    @PostMapping("/equipes")
    public ResponseEntity<Equipe> addEquipe(@Valid @RequestBody EquipeDTO equipeDto, BindingResult result ) {
        System.out.println("add equipe " + equipeDto    );

        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        Equipe newEquipe = null;
        try {
            newEquipe = equipeService.addEquipe(equipeDto);
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newEquipe);
    }

    //US004
    @GetMapping("/equipes/{nom}")
    public ResponseEntity<Optional<Equipe>> findEquipeByNom(@PathVariable("nom") String nom){

        // On aurait pu faire cette fonction avec un Optional.
        // C'est une liste avec une seul valeur, jamais null, permettant de comparer rapidement. Mais le try/catch fonctionne aussi

        try{
            return new ResponseEntity<Optional<Equipe>>(equipeService.findEquipeByNom(nom), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<Optional<Equipe>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/equipes/{noEquipe}")
    public ResponseEntity<?> deleteEquipe(@PathVariable("noEquipe") Integer noEquipe) {
        equipeService.deleteEquipe(noEquipe);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
