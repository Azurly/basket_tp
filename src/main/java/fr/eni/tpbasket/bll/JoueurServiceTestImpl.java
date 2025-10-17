package fr.eni.tpbasket.bll;

import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.bo.Joueur;
import fr.eni.tpbasket.dto.JoueurDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Service
public class JoueurServiceTestImpl implements JoueurService{

    private Set<Joueur> joueurs;

    public JoueurServiceTestImpl(){
        this.joueurs = new HashSet<Joueur>();
    }

    //US005
    @Override
    public List<Joueur> getJoueurs(){
        return joueurs.stream().toList();
    }

    //US006
    @Override
    public List<Joueur> getJoueursByNomEquipe(String nom){
        List<Joueur> results = new ArrayList<>();
        for(Joueur joueur : joueurs){
            if(joueur.getnEquipe().getNom().equals(nom)){
                results.add(joueur);
            }
        }
        return results;
    }

    //US007
    @Override
    public Joueur createJoueur(JoueurDTO joueurDto){
        Joueur newJoueur = new Joueur();
        BeanUtils.copyProperties(joueurDto, newJoueur);
        joueurs.add(newJoueur);
        return newJoueur;
    }

    //US008
    @Override
    public void deleteJoueur(Integer  numJoueur){
    }
}
