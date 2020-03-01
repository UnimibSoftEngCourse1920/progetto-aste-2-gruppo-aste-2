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
    private final AstaDAO astaDAO;

    @Autowired
    public PostgresOffertaDAO(JdbcTemplate jdbcTemplate, UtenteRegistratoDAO utenteRegistratoDAO, AstaDAO astaDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.utenteRegistratoDAO = utenteRegistratoDAO;
        this.astaDAO = astaDAO;
    }

    @Override
    public int inserisciOfferta(UUID id, UUID idAsta, OffertaModel offerta) {
        final String sql = "INSERT INTO offerta(id,id_utente_registrato,id_asta,data_offerta,credito_offerto)" +
                " VALUES(?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,offerta.getUtenteOfferente().getId(),idAsta,offerta.getDataOfferta(),offerta.getCreditoOfferto());
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

                    UUID idUtenteRegistrato = UUID.fromString(resultSet.getString("id_utente_registrato"));
                    Optional<UtenteRegistratoModel> optionalUtenteOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idUtenteRegistrato);
                    UtenteRegistratoModel utenteOfferente;
                    if(optionalUtenteOfferente.isPresent())
                        utenteOfferente = optionalUtenteOfferente.get();
                    else
                        return null;
                    // TODO: throw OfferenteNotFoundException

                    Date dataOfferta = resultSet.getDate("data_offerta");
                    float creditoOfferto = resultSet.getFloat("credito_offerto");
                    return new OffertaModel(idTrovato, creditoOfferto, dataOfferta, utenteOfferente);
                });
        return Optional.ofNullable(offertaTrovata);
    }

    @Override
    public List<OffertaModel> trovaTutteOfferte() {
        final String sql = "SELECT * FROM offerta";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID idTrovato = UUID.fromString(resultSet.getString("id"));

            UUID idUtenteRegistrato = UUID.fromString(resultSet.getString("id_utente_registrato"));
            Optional<UtenteRegistratoModel> optionalUtenteOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idUtenteRegistrato);
            UtenteRegistratoModel utenteOfferente;
            if(optionalUtenteOfferente.isPresent())
                utenteOfferente = optionalUtenteOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(idTrovato, creditoOfferto, dataOfferta, utenteOfferente);
        });
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteAsta(UUID idAsta) {
        final String sql = "SELECT * FROM offerta WHERE id_asta = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID idTrovato = UUID.fromString(resultSet.getString("id"));

            UUID idUtenteRegistrato = UUID.fromString(resultSet.getString("id_utente_registrato"));
            Optional<UtenteRegistratoModel> optionalUtenteOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idUtenteRegistrato);
            UtenteRegistratoModel utenteOfferente;
            if(optionalUtenteOfferente.isPresent())
                utenteOfferente = optionalUtenteOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(idTrovato, creditoOfferto, dataOfferta, utenteOfferente);
        });
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteUtente(UUID idUtenteOfferente) {
        final String sql = "SELECT * FROM offerta WHERE id_utente_registrato = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID idTrovato = UUID.fromString(resultSet.getString("id"));

            Optional<UtenteRegistratoModel> optionalUtenteOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idUtenteOfferente);
            UtenteRegistratoModel utenteOfferente;
            if(optionalUtenteOfferente.isPresent())
                utenteOfferente = optionalUtenteOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(idTrovato, creditoOfferto, dataOfferta, utenteOfferente);
        });
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteUtenteAsta(UUID idUtenteOfferente, UUID idAsta) {
        final String sql = "SELECT * FROM offerta WHERE id_utente_registrato = ? AND id_asta = ?";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID idTrovato = UUID.fromString(resultSet.getString("id"));

            Optional<UtenteRegistratoModel> optionalUtenteOfferente = utenteRegistratoDAO.trovaUtenteRegistrato(idUtenteOfferente);
            UtenteRegistratoModel utenteOfferente;
            if(optionalUtenteOfferente.isPresent())
                utenteOfferente = optionalUtenteOfferente.get();
            else
                return null;
            // TODO: throw OfferenteNotFoundException

            Date dataOfferta = resultSet.getDate("data_offerta");
            float creditoOfferto = resultSet.getFloat("credito_offerto");
            return new OffertaModel(idTrovato, creditoOfferto, dataOfferta, utenteOfferente);
        });
    }

    @Override
    public int aggiornaOfferta(UUID id, UUID idAsta, OffertaModel offertaAggiornata) {
        final String sql = "UPDATE offerta SET id_utente_registrato = ?, id_asta = ?, data_offerta = ?, credito_offerto = ? WHERE id = ?";
        return jdbcTemplate.update(sql,offertaAggiornata.getUtenteOfferente().getId(), idAsta,
                offertaAggiornata.getDataOfferta(), offertaAggiornata.getCreditoOfferto(), id);
    }
}
