package fr.eni.tpbasket.controllers;

import fr.eni.tpbasket.bll.JoueurService;
import fr.eni.tpbasket.bo.Joueur;
import fr.eni.tpbasket.dto.JoueurDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JoueurRestController {

    private JoueurService joueurService;

    public JoueurRestController(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    //US005
    @GetMapping("/joueurs")
    public List<Joueur> getJoueurs(){
        return joueurService.getJoueurs();
    }

    //US006
    @GetMapping("/joueurs-")
    @ResponseBody
    public List<Joueur> allJoueurByEquipe(@RequestParam String nomEquipe){
        return joueurService.getJoueursByNomEquipe(nomEquipe);
    }

    //US007
    @PostMapping("/joueurs")
    public ResponseEntity<?> createJoueur(@RequestBody JoueurDTO joueurDto){
        System.out.println("Joueur created "+joueurDto);
        joueurService.createJoueur(joueurDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(joueurDto);
    }

    /*//US008
    @DeleteMapping("/joueurs/{email}")
    public ResponseEntity<?> deleteJoueur(@PathVariable String email){
        System.out.println("Joueur delete "+email);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }*/

    //US0015
    @DeleteMapping("/joueurs/{id}")
    public ResponseEntity<?> deleteJoueur(@PathVariable("id") Integer id){
        System.out.println("Joueur deleted "+ id);
        joueurService.deleteJoueur(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
