package fr.eni.tpbasket.bll;

import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.dal.EquipeDAO;
import fr.eni.tpbasket.dto.EquipeDTO;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.springframework.context.annotation.Primary;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class EquipeServiceImpl implements EquipeService {


    private EquipeDAO equipeDAO;

    public EquipeServiceImpl(EquipeDAO equipeDAO) {
        this.equipeDAO = equipeDAO;

    }

    @Override
    public List<Equipe> getEquipes() {
        return equipeDAO.findAllEquipes();
    }

    @Override
    public Equipe addEquipe(EquipeDTO equipeDto) {
        return equipeDAO.addEquipe(equipeDto);
    }

    @Override
    public void deleteEquipe(Integer noEquipe) {
        equipeDAO.deleteEquipe(noEquipe);
    }

    @Override
    public Optional<Equipe> findEquipeByNom(String nom) {
        return equipeDAO.findEquipeByNom(nom);
    }
}
