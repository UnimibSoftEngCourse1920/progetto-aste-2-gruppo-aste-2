package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.model.OffertaModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
    public int inserisciOfferta(UUID id, UUID idAsta, OffertaModel offerta) {
        final String sql = "INSERT INTO offerta(id,id_offerente,id_asta,data_offerta,credito_offerto)" +
                " VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,offerta.getOfferente().getId(),idAsta,offerta.getDataOfferta(),offerta.getCreditoOfferto());
    }

    @Override
    public int eliminaOfferta(UUID id) {
        final String sql = "DELETE FROM offerta WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<OffertaModel> trovaOfferta(UUID id) {
        final String sql = "SELECT * FROM offerta WHERE id = ?";
        OffertaModel offertaTrovata = jdbcTemplate.queryForObject(
                sql, new Object[]{id},
                (resultSet, i) -> {
                    UUID idTrovato = UUID.fromString(resultSet.getString("id"));

                    UUID idOfferente = UUID.fromString(resultSet.getString("id_offerente"));
                    Optional<UtenteRegistratoModel> optionalOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idOfferente);
                    UtenteRegistratoModel offerente;
                    if(optionalOfferente.isPresent())
                        offerente = optionalOfferente.get();
                    else
                        return null;
                    // TODO: throw OfferenteNotFoundException

                    Date dataOfferta = resultSet.getDate("data_offerta");
                    float creditoOfferto = resultSet.getFloat("credito_offerto");
                    return new OffertaModel(idTrovato, creditoOfferto, dataOfferta, offerente);
                });
        return Optional.ofNullable(offertaTrovata);
    }

    @Override
    public List<OffertaModel> trovaTutteOfferte() {
        final String sql = "SELECT * FROM offerta";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));

            UUID idUtenteRegistrato = UUID.fromString(resultSet.getString("id_offerente"));
            Optional<UtenteRegistratoModel> optionalUtenteOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idUtenteRegistrato);
            UtenteRegistratoModel utenteOfferente;
            if(optionalUtenteOfferente.isPresent())
                utenteOfferente = optionalUtenteOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(id, creditoOfferto, dataOfferta, utenteOfferente);
        });
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteAsta(UUID idAsta) {
        final String sql = "SELECT * FROM offerta WHERE id_asta = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));

            UUID idOfferente = UUID.fromString(resultSet.getString("id_offerente"));
            Optional<UtenteRegistratoModel> optionalOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idOfferente);
            UtenteRegistratoModel offerente;
            if(optionalOfferente.isPresent())
                offerente = optionalOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(id, creditoOfferto, dataOfferta, offerente);
        });
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteUtente(UUID idOfferente) {
        final String sql = "SELECT * FROM offerta WHERE id_offerente = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));

            Optional<UtenteRegistratoModel> optionalOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idOfferente);
            UtenteRegistratoModel offerente;
            if(optionalOfferente.isPresent())
                offerente = optionalOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(id, creditoOfferto, dataOfferta, offerente);
        });
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteUtenteAsta(UUID idOfferente, UUID idAsta) {
        final String sql = "SELECT * FROM offerta WHERE id_offerente = ? AND id_asta = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));

            Optional<UtenteRegistratoModel> optionalOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idOfferente);
            UtenteRegistratoModel offerente;
            if(optionalOfferente.isPresent())
                offerente = optionalOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(id, creditoOfferto, dataOfferta, offerente);
        });
    }

    @Override
    public int aggiornaOfferta(UUID id, OffertaModel offertaAggiornata) {
        final String sql = "UPDATE offerta SET id_offerente = ?, data_offerta = ?, credito_offerto = ? WHERE id = ?";
        return jdbcTemplate.update(sql,offertaAggiornata.getOfferente().getId(), offertaAggiornata.getDataOfferta(),
                offertaAggiornata.getCreditoOfferto(), id);
    }
}
