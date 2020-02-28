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
    public int eliminaUtenteRegistrato(UUID id) {
        final String sql = "DELETE FROM utente_registrato WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID id) {
        final String sql = "SELECT * FROM utente_registrato WHERE id = ?";
        UtenteRegistratoModel utenteTrovato = jdbcTemplate.queryForObject(
                sql, new Object[]{id},
                (resultSet, i) -> {
                    UUID idTrovato = UUID.fromString(resultSet.getString("id"));
                    String username = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String telefono = resultSet.getString("telefono");
                    String password = resultSet.getString("password");
                    float credito = resultSet.getFloat("credito_disponibile");
                    return new UtenteRegistratoModel(idTrovato, username, email, telefono, password, credito);
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
            float credito = resultSet.getFloat("credito_disponibile");
            return new UtenteRegistratoModel(id, username, email, telefono, password, credito);
        });
    }

    // Quando chiamato anche se magari cambio solo un valore per l'utente devo comunque specificare tutti gli altri
    // altrimenti di default sono null che il database non accetta (per i vincoli di not null)
    @Override
    public int aggiornaUtenteRegistrato(UUID id, UtenteRegistratoModel utenteAggiornato) {
        final String sql = "UPDATE utente_registrato SET username = ?, password = ?, email = ?, telefono = ?, credito_disponibile = ? WHERE id = ?";
        return jdbcTemplate.update(sql,utenteAggiornato.getUsername(), utenteAggiornato.getPassword(), utenteAggiornato.getEmail(),
                utenteAggiornato.getNumeroTelefono(), utenteAggiornato.getCredito(), id);
    }

    @Override
    public boolean controllaUsernameOccupato(String username) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM utente_registrato WHERE username = ?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class,username);
    }

    @Override
    public boolean controllaEmailOccupata(String email) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM utente_registrato WHERE email = ?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class, email);
    }

    @Override
    public boolean controllaUtenteEsiste(UtenteRegistratoModel utente) {
        final String sql = "SELECT EXISTS(SELECT 1 FROM utente_registrato WHERE username = ? AND email = ? AND password = ?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class, utente.getUsername(), utente.getEmail(), utente.getPassword());
    }

    @Override
    public int aggiungiCredito(UUID id, float creditoAggiunto) {
        final float credito = creditoAggiunto + creditoDisponibile(id);
        final String sql = "UPDATE utente_registrato SET credito_disponibile = ? WHERE id = ?";
        return jdbcTemplate.update(sql, credito, id);
    }

    @Override
    public float creditoDisponibile(UUID id) {
        return creditoTotale(id) - creditoImpegnato(id);
    }

    @Override
    public float creditoImpegnato(UUID id) {
        final String sql =
                "SELECT o.credito_offerto FROM offerta as o, asta as a WHERE o.id_utente_registrato = ? AND o.id_asta = a.id AND a.data_fine is null";

        float creditoImpegnato = 0;

        List<Float> creditoOfferte = jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    return resultSet.getFloat("credito_offerto");
                },
                id);

        for(float offerta : creditoOfferte)
        {
            creditoImpegnato += offerta;
        }

        return creditoImpegnato;
    }

    @Override
    public float creditoTotale(UUID id) {
        final String sql = "SELECT credito_disponibile FROM utente_registrato WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, Float.class, id);
    }
}
