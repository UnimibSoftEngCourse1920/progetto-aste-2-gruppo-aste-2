package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.PortafoglioModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository("postgres-utenteRegistrato")
public class PostgresUtenteRegistratoDAO implements UtenteRegistratoDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresUtenteRegistratoDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean insersciUtenteRegistrato(UtenteRegistratoModel utenteRegistrato) {
        return false;
    }

    @Override
    public boolean eliminaUtenteRegistrato(UUID id) {
        return false;
    }

    @Override
    public UtenteRegistratoModel trovaUtenteRegistro(UUID id) {
        return null;
    }

    @Override
    public List<UtenteRegistratoModel> trovaTuttiUtentiRegistrati() {
        final String sql = "SELECT * FROM utenteregistrato";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String telefono = resultSet.getString("telefono");
            String password = resultSet.getString("password");
            double credito = resultSet.getDouble("creditodisponibile");
            return new UtenteRegistratoModel(id, username, email, telefono, password, new PortafoglioModel(credito));
        });
    }

    @Override
    public boolean aggiornaUtenteRegistrto(UUID id, UtenteRegistratoModel utenteAggiornato) {
        return false;
    }
}
