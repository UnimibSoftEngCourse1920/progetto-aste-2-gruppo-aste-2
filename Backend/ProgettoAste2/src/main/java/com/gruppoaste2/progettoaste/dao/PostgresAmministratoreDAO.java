package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-amministratore")
public class PostgresAmministratoreDAO implements AmministratoreDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresAmministratoreDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID inserisciAmministratore(UUID idAmministratore, AmministratoreModel amministratore) {
        final String sql = "INSERT INTO amministratore(id, username, password, email) " +
                "VALUES(?, ?, ?, ?)";
        if(jdbcTemplate.update(sql,
                idAmministratore, amministratore.getUsername(), amministratore.getPassword(), amministratore.getEmail())
                == 0)
            return null;
        return idAmministratore;
    }

    @Override
    public int eliminaAmministratore(UUID idAmministratore) {
        final String sql = "DELETE FROM amministratore WHERE id = ?";
        return jdbcTemplate.update(sql, idAmministratore);
    }

    @Override
    public Optional<AmministratoreModel> trovaAmministratore(UUID idAmministratore) {
        final String sql = "SELECT * FROM amministratore WHERE id = ?";
         List<AmministratoreModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeAmministratoreFromResultSet(resultSet),
                 idAmministratore);
        AmministratoreModel returnable = (results.isEmpty()) ? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<AmministratoreModel> trovaAmministratori() {
        final String sql = "SELECT * FROM amministratore";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAmministratoreFromResultSet(resultSet));
    }

    @Override
    public int aggiornaAmministratore(UUID idAmministratore, AmministratoreModel amministratoreAggiornato) {
        final String sql = "UPDATE utente_registrato SET username = ?, password = ?, email = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                amministratoreAggiornato.getUsername(), amministratoreAggiornato.getPassword(),
                amministratoreAggiornato.getEmail(), idAmministratore);
    }

    @Override
    public boolean controllaUsernameOccupato(String username) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM amministratore WHERE username = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, username);
    }

    @Override
    public boolean controllaEmailOccupata(String email) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM amministratore WHERE email = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    @Override
    public boolean controllaAmministratoreEsiste(AmministratoreModel amministratore) {
        final String sql = "SELECT EXISTS" +
                "(SELECT 1 FROM amministratore WHERE username = ? AND email = ? AND password = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class,
                amministratore.getUsername(), amministratore.getEmail(), amministratore.getPassword());
    }

    @Override
    public UUID ritornaIdAmministratore(AmministratoreModel amministratore) {
        final String sql = "SELECT id FROM amministratore WHERE username = ? AND email = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, UUID.class,
                amministratore.getUsername(), amministratore.getEmail(), amministratore.getPassword());
    }

    private AmministratoreModel makeAmministratoreFromResultSet(ResultSet resultSet) throws SQLException {
        UUID idAmministratore = UUID.fromString(resultSet.getString("id"));
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new AmministratoreModel(idAmministratore, username, email, password);
    }
}
