package fr.eni.tpbasket.bll;

import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.dto.EquipeDTO;

import java.util.List;
import java.util.Optional;

public interface EquipeService {

    /* Retourne la liste de toutes les équipes */
    public abstract List<Equipe> getEquipes();

    /*Ajouter une équipe à la liste des équipes*/
    public abstract Equipe addEquipe(EquipeDTO equipeDto);

    public abstract Optional<Equipe> findEquipeByNom(String nom);

    void deleteEquipe(Integer noEquipe);
}
