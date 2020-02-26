package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
    public int inserisciAmministratore(UUID id, AmministratoreModel amministratore) {
        final String sql = "INSERT INTO amministratore(id,username,password,email)" +
                " VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,amministratore.getUsername(),amministratore.getPassword(),amministratore.getEmail());

    }

    @Override
    public boolean eliminaAmministratore(UUID id) {
        return false;
    }

    @Override
    public Optional<AmministratoreModel> trovaAmministratore(UUID id) {
        final String sql = "SELECT * FROM amministratore WHERE id = ?";
        AmministratoreModel ammTrovato = jdbcTemplate.queryForObject(sql,
        (resultSet, i) -> {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            return new AmministratoreModel(id,username,email,password);
        },
                id);
        return  Optional.ofNullable(ammTrovato);
    }

    @Override
    public Optional<List<AmministratoreModel>> trovaAmministratori() {
        final String sql = "SELECT * FROM amministratore";
        List<AmministratoreModel> listAmmTrovati = jdbcTemplate.query(sql, (resultSet, i) ->
        {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String email = resultSet.getString("email");
            return new AmministratoreModel(id,username,email,password);
        });

        return Optional.ofNullable(listAmmTrovati);
    }


    @Override
    public boolean aggiornaAmministratore(UUID id, AmministratoreModel amministratoreAggiornato) {
        return false;
    }

    @Override
    public boolean controllaUsernameOccupato(String username) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM amministratore WHERE username = ?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class,username);
    }

    @Override
    public boolean controllaEmailOccupata(String email) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM amministratore WHERE email = ?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class,email);
    }

    @Override
    public boolean controllaAmministratoreEsiste(AmministratoreModel amministratore) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM amministratore WHERE username = ? AND email = ? AND password = ?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class, amministratore.getUsername(), amministratore.getEmail(), amministratore.getPassword());
    }
}
