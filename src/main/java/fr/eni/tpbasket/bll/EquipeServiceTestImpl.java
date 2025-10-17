package fr.eni.tpbasket.bll;

import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.dto.EquipeDTO;
import org.springframework.beans.BeanUtils;

import java.util.*;

//@Service
public class EquipeServiceTestImpl implements EquipeService {

    private Set<Equipe> equipes;

    private EquipeServiceTestImpl() {
        this.equipes = new HashSet<Equipe>();
        this.equipes.add(new Equipe(1, "U15F1"));
        this.equipes.add(new Equipe(2, "U15F2"));
        this.equipes.add(new Equipe(3, "U18M1"));
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = new HashSet<>(equipes);
    }

    @Override
    public List<Equipe> getEquipes() {
        return equipes.stream().toList();
    }

    @Override
    public Equipe addEquipe(EquipeDTO equipeDto) {
        Equipe newEquipe = new Equipe();
        BeanUtils.copyProperties(equipeDto, newEquipe);
        equipes.add(newEquipe);
        return newEquipe;
    }

    @Override
    public Optional<Equipe> findEquipeByNom(String nom) {
         return equipes.stream().filter(equipe -> equipe.getNom().equals(nom)).findFirst();
    }

    @Override
    public void deleteEquipe(Integer noEquipe) {

    }
}
