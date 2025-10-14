package fr.eni.tpbasket.bll;

import fr.eni.tpbasket.bo.Equipe;

import java.util.List;

public interface EquipeService {

    /* Retourne la liste de toutes les équipes */
    public abstract List<Equipe> getEquipes();
}
