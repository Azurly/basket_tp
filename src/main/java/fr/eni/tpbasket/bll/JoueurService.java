package fr.eni.tpbasket.bll;

import fr.eni.tpbasket.bo.Joueur;
import fr.eni.tpbasket.dto.JoueurDTO;

import java.util.List;

public interface JoueurService {
    public abstract List<Joueur> getJoueurs();

    public abstract List<Joueur> getJoueursByNomEquipe(String nom);

    public abstract Joueur createJoueur(JoueurDTO joueurDto);

    public abstract void deleteJoueur(Integer noJoueur);
}
