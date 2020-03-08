package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OffertaModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-offerta")
public class PostgresOffertaDAO implements OffertaDAO{

    private final JdbcTemplate jdbcTemplate;

    private final UtenteRegistratoDAO utenteRegistratoDAO;

    @Autowired
    public PostgresOffertaDAO(JdbcTemplate jdbcTemplate, UtenteRegistratoDAO utenteRegistratoDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.utenteRegistratoDAO = utenteRegistratoDAO;
    }

    @Override
    public int aggiungiOfferta(UUID id, UUID idAsta, OffertaModel offerta) {
        final String sql = "INSERT INTO offerta(id, id_offerente, id_asta, data_offerta, credito_offerto) " +
                "VALUES(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                id, offerta.getOfferente().getId(), idAsta, offerta.getDataOfferta(), offerta.getCreditoOfferto());
    }

    @Override
    public int eliminaOfferta(UUID id) {
        final String sql = "DELETE FROM offerta WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<OffertaModel> trovaOfferta(UUID id) {
        final String sql = "SELECT * FROM offerta WHERE id = ?";
        List<OffertaModel> results = jdbcTemplate.query(
                sql, (resultSet, i) -> makeOffertaFromResultSet(resultSet),
                id);
        OffertaModel returnable = (results.isEmpty())? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<OffertaModel> trovaOfferte() {
        final String sql = "SELECT * FROM offerta";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOffertaFromResultSet(resultSet));
    }

    @Override
    public List<OffertaModel> trovaOfferteAsta(UUID idAsta) {
        final String sql = "SELECT * FROM offerta WHERE id_asta = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOffertaFromResultSet(resultSet),
                idAsta);
    }

    @Override
    public Optional<OffertaModel> trovaUltimaOffertaAsta(UUID idAsta) {
        final String sql = "SELECT * FROM offerta WHERE id_asta = ? ORDER BY data_offerta DESC LIMIT 1";
        List<OffertaModel> results = jdbcTemplate.query(
                sql, (resultSet, i) -> makeOffertaFromResultSet(resultSet),
                idAsta);
        OffertaModel returnable = (results.isEmpty())? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<OffertaModel> trovaOfferteUtente(UUID idOfferente) {
        final String sql = "SELECT * FROM offerta WHERE id_offerente = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOffertaFromResultSet(resultSet),
                idOfferente);
    }

    @Override
    public List<OffertaModel> trovaOfferteUtenteAsta(UUID idOfferente, UUID idAsta) {
        final String sql = "SELECT * FROM offerta WHERE id_offerente = ? AND id_asta = ?";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeOffertaFromResultSet(resultSet),
                idOfferente, idAsta);
    }

    @Override
    public int aggiornaOfferta(UUID id, OffertaModel offertaAggiornata) {
        final String sql = "UPDATE offerta SET id_offerente = ?, data_offerta = ?, credito_offerto = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                offertaAggiornata.getOfferente().getId(), offertaAggiornata.getDataOfferta(),
                offertaAggiornata.getCreditoOfferto(), id);
    }

    private OffertaModel makeOffertaFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));

        UUID idOfferente = UUID.fromString(resultSet.getString("id_offerente"));
        UtenteRegistratoModel offerente = utenteRegistratoDAO.trovaUtenteRegistrato(idOfferente)
                .orElse(null);

        Date dataOfferta = resultSet.getDate("data_offerta");
        float creditoOfferto = resultSet.getFloat("credito_offerto");
        return new OffertaModel(id, creditoOfferto, dataOfferta, offerente);
    }
}
