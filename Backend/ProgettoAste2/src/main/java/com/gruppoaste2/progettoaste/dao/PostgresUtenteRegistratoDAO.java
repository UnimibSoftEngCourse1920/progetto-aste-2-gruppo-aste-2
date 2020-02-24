package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-utenteRegistrato")
public class PostgresUtenteRegistratoDAO implements UtenteRegistratoDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresUtenteRegistratoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int inserisciUtenteRegistrato(UUID id, UtenteRegistratoModel utenteRegistrato) {
        final String sql = "INSERT INTO utente_registrato(id,username,password,email,telefono,credito_disponibile)" +
                " VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,utenteRegistrato.getUsername(),utenteRegistrato.getPassword(),utenteRegistrato.getEmail(),
                utenteRegistrato.getNumeroTelefono(),utenteRegistrato.getCredito());
    }

    @Override
    public boolean eliminaUtenteRegistrato(UUID id) {
        return false;
    }

    @Override
    public Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID id) {
        final String sql = "SELECT * FROM utente_registrato WHERE id = ?";
        UtenteRegistratoModel utenteTrovato = jdbcTemplate.queryForObject(
                sql, new Object[]{id},
                (resultSet, i) -> {
                    UUID id_d = UUID.fromString(resultSet.getString("id"));
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String telefono = resultSet.getString("telefono");
                    String password = resultSet.getString("password");
                    double credito = resultSet.getDouble("credito_disponibile");
                    return new UtenteRegistratoModel(id_d, username, email, telefono, password, credito);
                });
        return Optional.ofNullable(utenteTrovato);
    }

    @Override
    public List<UtenteRegistratoModel> trovaTuttiUtentiRegistrati() {
        final String sql = "SELECT * FROM utente_registrato";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String password = resultSet.getString("password");
            double credito = resultSet.getDouble("credito_disponibile");
            return new UtenteRegistratoModel(id, username, email, telefono, password, credito);
        });
    }

    @Override
    public boolean aggiornaUtenteRegistrato(UUID id, UtenteRegistratoModel utenteAggiornato) {
        return false;
    }
}
