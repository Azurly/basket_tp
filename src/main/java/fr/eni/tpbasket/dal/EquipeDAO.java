package fr.eni.tpbasket.dal;

import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.dto.EquipeDTO;

import java.util.List;
import java.util.Optional;

public interface EquipeDAO {
    public abstract List<Equipe> findAllEquipes();
    public abstract Optional<Equipe> findEquipeByNom(String nom);
    public abstract Equipe addEquipe(EquipeDTO equipeDto);
    public abstract void deleteEquipe(Integer noEquipe);
}
