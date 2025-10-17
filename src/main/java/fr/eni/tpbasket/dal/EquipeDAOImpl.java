package fr.eni.tpbasket.dal;

import fr.eni.tpbasket.bo.Equipe;
import fr.eni.tpbasket.dto.EquipeDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
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
import java.util.Optional;

//Repository parce que c'est un DAO, donc il doit lier la bdd avec le programme
@Repository
public class EquipeDAOImpl implements EquipeDAO {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EquipeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Equipe> findAllEquipes(){
        String sql = "SELECT noEquipe, nom FROM equipes";
        List<Equipe> equipes ;
        try{
            equipes = jdbcTemplate.query(sql, new EquipeRowMapper());
        } catch (Exception e) {
            return null;
        }
        return equipes;
    }

    @Override
    public Optional<Equipe> findEquipeByNom(String nom){
        String sql = "SELECT noEquipe, nom FROM equipes WHERE nom =? ";
        Equipe equipe;
        try {
            equipe = jdbcTemplate.queryForObject(sql, new EquipeRowMapper(), nom);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.ofNullable(equipe);
    };

    @Override
    public void deleteEquipe(Integer noEquipe){
        String sql = "DELETE FROM equipes WHERE noEquipe =? ";
        try{
            jdbcTemplate.update(sql, noEquipe);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Equipe addEquipe(EquipeDTO equipeDto) {
    String query = "INSERT INTO equipes (nom) VALUES (:nom)";

    Equipe newEquipe;
    KeyHolder keyHolder = new GeneratedKeyHolder(); // Pour la clé primaire
    Map<String, Object> params = new HashMap();
        params.put("nom", equipeDto.nom());

        try{
        namedParameterJdbcTemplate.update(
                query,
                new MapSqlParameterSource(params),
                keyHolder,
                new String[]{"id"}
        );
        newEquipe = new Equipe();
        BeanUtils.copyProperties(equipeDto, newEquipe);
        newEquipe.setNoEquipe(keyHolder.getKey().intValue());
    } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return newEquipe;
    }


    class EquipeRowMapper implements RowMapper<Equipe> {

        @Override
        public Equipe mapRow(ResultSet rs, int rowNum) throws SQLException {
            Equipe equipe = new Equipe();
            equipe.setNoEquipe(rs.getInt("noEquipe"));
            equipe.setNom(rs.getString("nom"));
            return equipe;
        }
    }
}
