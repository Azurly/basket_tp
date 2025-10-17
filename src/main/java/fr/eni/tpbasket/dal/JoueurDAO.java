package fr.eni.tpbasket.dal;

import fr.eni.tpbasket.bo.Joueur;
import fr.eni.tpbasket.dto.JoueurDTO;

import java.util.List;

public interface JoueurDAO {
    List<Joueur> findAllJoueurs();
    List<Joueur> findJoueursByEquipe(String nom);
    void deleteJoueur(Integer noJoueur);
    Joueur addJoueur(JoueurDTO joueurDto);
}
