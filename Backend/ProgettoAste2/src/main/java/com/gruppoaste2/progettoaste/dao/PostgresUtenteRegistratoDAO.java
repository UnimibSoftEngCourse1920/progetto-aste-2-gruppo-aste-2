package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
    public int aggiungiUtenteRegistrato(UUID id, UtenteRegistratoModel utenteRegistrato) {
        final String sql = "INSERT INTO utente_registrato" +
                "(id, username, password, email, telefono, credito_disponibile) " +
                "VALUES(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                id, utenteRegistrato.getUsername(), utenteRegistrato.getPassword(), utenteRegistrato.getEmail(),
                utenteRegistrato.getNumeroTelefono(), utenteRegistrato.getCredito());
    }

    @Override
    public int eliminaUtenteRegistrato(UUID id) {
        final String sql = "DELETE FROM utente_registrato WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID id) {
        final String sql = "SELECT * FROM utente_registrato WHERE id = ?";
        List<UtenteRegistratoModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeUtenteRegistratoFromResultSet(resultSet),
                id);
        UtenteRegistratoModel returnable = results.isEmpty() ? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<UtenteRegistratoModel> trovaUtentiRegistrati() {
        final String sql = "SELECT * FROM utente_registrato";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeUtenteRegistratoFromResultSet(resultSet));
    }

    @Override
    public int aggiornaUtenteRegistrato(UUID id, UtenteRegistratoModel utenteAggiornato) {
        final String sql = "UPDATE utente_registrato " +
                "SET username = ?, password = ?, email = ?, telefono = ?, credito_disponibile = ?, notifica_sms = ?, notifica_email = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql,
                utenteAggiornato.getUsername(), utenteAggiornato.getPassword(), utenteAggiornato.getEmail(),
                utenteAggiornato.getNumeroTelefono(), utenteAggiornato.getCredito(), utenteAggiornato.isNotificheSms(), utenteAggiornato.isNotificheEmail(), id);
    }

    @Override
    public boolean controllaUsernameOccupato(String username) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM utente_registrato WHERE username = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, username);
    }

    @Override
    public boolean controllaEmailOccupata(String email) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM utente_registrato WHERE email = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    @Override
    public boolean controllaUtenteEsiste(UtenteRegistratoModel utente) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM utente_registrato " +
                "WHERE username = ? AND email = ? AND password = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class,
                utente.getUsername(), utente.getEmail(), utente.getPassword());
    }

    @Override
    public UUID ritornaIdUtenteRegistrato(UtenteRegistratoModel utente) {
        final String sql = "SELECT id FROM utente_registrato WHERE username = ? AND email = ? AND password = ?";
        return jdbcTemplate.queryForObject(sql, UUID.class,
                utente.getUsername(), utente.getEmail(), utente.getPassword());
    }

    @Override
    public int aggiungiCredito(UUID id, float creditoAggiunto) {
        final float credito = creditoAggiunto + creditoTotale(id);
        final String sql = "UPDATE utente_registrato SET credito_disponibile = ? WHERE id = ?";
        return jdbcTemplate.update(sql, credito, id);
    }

    @Override
    public float creditoTotale(UUID id) {
        final String sql = "SELECT credito_disponibile FROM utente_registrato WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Float.class, id);
    }
    
    @Override
    public float creditoImpegnato(UUID id) {
        final String sql = "SELECT o.credito_offerto FROM offerta AS o, asta AS a " +
                "WHERE o.id_offerente = ? AND o.id_asta = a.id AND a.data_fine IS NULL";

        float creditoImpegnato = 0;

        List<Float> creditoOfferte = jdbcTemplate.query(sql,
                (resultSet, i) -> resultSet.getFloat("credito_offerto"),
                id);

        for(float offerta : creditoOfferte)
            creditoImpegnato += offerta;

        return creditoImpegnato;
    }

    @Override
    public boolean isNotificheEmailAbilitate(UUID id) {
        final String sql = "SELECT notifica_email FROM utente_registrato WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    @Override
    public boolean isNotificheSmsAbilitate(UUID id) {
        final String sql = "SELECT notifica_sms FROM utente_registrato WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    private UtenteRegistratoModel makeUtenteRegistratoFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String numeroTelefono = resultSet.getString("telefono");
        String password = resultSet.getString("password");
        float credito = resultSet.getFloat("credito_disponibile");
        boolean notificheEmail = resultSet.getBoolean("notifica_email");
        boolean notificheSms = resultSet.getBoolean("notifica_sms");
        return new UtenteRegistratoModel(id, username, email, password, numeroTelefono, credito,
                notificheEmail, notificheSms);
    }
}
