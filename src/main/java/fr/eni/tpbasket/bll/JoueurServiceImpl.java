package fr.eni.tpbasket.bll;

import fr.eni.tpbasket.bo.Joueur;
import fr.eni.tpbasket.dal.JoueurDAO;
import fr.eni.tpbasket.dto.JoueurDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class JoueurServiceImpl implements JoueurService{

    private JoueurDAO joueurDAO;

    public JoueurServiceImpl(JoueurDAO joueurDAO){
        this.joueurDAO = joueurDAO;
    }

    @Override
    public List<Joueur> getJoueurs() {
        return joueurDAO.findAllJoueurs();
    }

    public List<Joueur> getJoueursByNomEquipe(String nom){
        return joueurDAO.findJoueursByEquipe(nom);
    };

    public Joueur createJoueur(JoueurDTO joueurDto){
        return joueurDAO.addJoueur(joueurDto);
    };

    public void deleteJoueur(Integer noJoueur){
        joueurDAO.deleteJoueur(noJoueur);
    };

}
