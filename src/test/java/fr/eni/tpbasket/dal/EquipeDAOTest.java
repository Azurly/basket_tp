package fr.eni.tpbasket.dal;

import fr.eni.tpbasket.bo.Equipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EquipeDAOTest {

    @Autowired
    private EquipeDAO equipeDAO;

    //Test via la liaison BDD
    @Test
    @DisplayName("Test DAO de findEquipeByNom")
    public void testFindEquipeByNom() {
        //Act
        Optional<Equipe> equipe = equipeDAO.findEquipeByNom("U15F1");

        //Asert
        assertTrue(equipe.isPresent());
        assertEquals(equipe.get().getNom(), "U15F1");
    }

    @Test
    @DisplayName("Test DAO de findEquipeByNom")
    public void testFindEquipeByNomNotExist() {
        //Act
        Optional<Equipe> equipe = equipeDAO.findEquipeByNom("U15F5");

        //Asert
        assertTrue(equipe.isPresent());
        assertEquals(equipe.get().getNom(), "U15F1");
    }


    //Test pour le code en brut
    public void testFindAllEquipes(){
        List<Equipe> equipes = equipeDAO.findAllEquipes();
        System.out.println(equipes);
        assertEquals(equipes.size(), 2);    }
}
