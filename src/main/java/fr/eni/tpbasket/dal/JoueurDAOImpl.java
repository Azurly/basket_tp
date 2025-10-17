package fr.eni.tpbasket.dal;

import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.bo.Joueur;
import fr.eni.tpbasket.dto.JoueurDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JoueurDAOImpl implements JoueurDAO {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    public JoueurDAOImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Joueur> findAllJoueurs(){
        String query = "SELECT j.noJoueur AS id, j.nom AS nomJoueur, j.prenom, j.email, j.noEquipe AS numeroEquipe, e.nom AS nomEquipe FROM joueurs AS j INNER JOIN equipes AS e ON j.noEquipe = e.noEquipe";
        List<Joueur> joueurs;
        try {
            joueurs = jdbcTemplate.query(query, new JoueurRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return joueurs;
    }

    @Override
    public List<Joueur> findJoueursByEquipe(String nom){
        String query = "SELECT j.noJoueur AS id, j.nom AS nomJoueur, j.prenom, j.email, j.noEquipe AS numeroEquipe, e.nom AS nomEquipe FROM joueurs AS j INNER JOIN equipes AS e ON j.noEquipe = e.noEquipe WHERE e.nom = ?";
        List<Joueur> joueurs;
        try {
            joueurs = jdbcTemplate.query(query, new JoueurRowMapper(), nom);
        }  catch (EmptyResultDataAccessException e) {
            return null;
        }
        return joueurs;
    }

    @Override
    public void deleteJoueur(Integer noJoueur){
        String query = "DELETE FROM joueurs WHERE noJoueur = ?";
        try {
            jdbcTemplate.update(query, noJoueur);
        } catch (EmptyResultDataAccessException e) {
            return;
        }
    }

    @Override
    public Joueur addJoueur(JoueurDTO joueurDto){
        String query = "INSERT INTO joueurs(nom, prenom, email, noEquipe) VALUES (:nom, :prenom, :email, :noEquipe)";

        Joueur newJoueur;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Map<String,Object> params = new HashMap<>();
        params.put("nom", joueurDto.nom());
        params.put("prenom", joueurDto.prenom());
        params.put("email", joueurDto.email());
        params.put("noEquipe", joueurDto.nEquipe());

        try{
            namedParameterJdbcTemplate.update(
                    query,
                    new MapSqlParameterSource(params),
                    keyHolder,
                    new String[]{"noJoueur"}
            );
            newJoueur = new Joueur();
            BeanUtils.copyProperties(joueurDto, newJoueur);
            newJoueur.setId(keyHolder.getKey().intValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newJoueur;
    }

    class JoueurRowMapper implements RowMapper<Joueur> {

        @Override
        public Joueur mapRow(ResultSet rs, int rowNum) throws SQLException {

            Joueur joueur = new Joueur();
            joueur.setId(rs.getInt("id"));
            joueur.setNom(rs.getString("nomJoueur"));
            joueur.setPrenom(rs.getString("prenom"));
            joueur.setEmail(rs.getString("email"));

            Equipe equipe = new Equipe();
            equipe.setNoEquipe(rs.getInt("numeroEquipe"));
            equipe.setNom(rs.getString("nomEquipe"));
            joueur.setnEquipe(equipe);

            return joueur;
        }
    }
}
