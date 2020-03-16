package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-asta")
public class PostgresAstaDAO implements AstaDAO {

    private final JdbcTemplate jdbcTemplate;

    private final UtenteRegistratoDAO utenteRegistratoDAO;
    private final ConfigurazioneDAO configurazioneDAO;
    private final OggettoDAO oggettoDAO;
    private final OffertaDAO offertaDAO;

    private final String SELECT_ALL_FROM_ASTA = "SELECT * FROM asta";

    @Autowired
    public PostgresAstaDAO(JdbcTemplate jdbcTemplate, UtenteRegistratoDAO utenteRegistratoDAO,
                           ConfigurazioneDAO configurazioneDAO, OggettoDAO oggettoDAO, OffertaDAO offertaDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.utenteRegistratoDAO = utenteRegistratoDAO;
        this.configurazioneDAO = configurazioneDAO;
        this.oggettoDAO = oggettoDAO;
        this.offertaDAO = offertaDAO;
    }

    @Override
    public UUID aggiungiAsta(UUID id, AstaModel asta) {
        final String sql = "INSERT INTO asta(id, id_asta_manager, id_configurazione, tipo, prezzo_partenza, " +
                "data_inizio, data_fine, durata_timeslot, rifiutata, criterio_terminazione) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?::tipoterminazioneasta)";
        jdbcTemplate.update(sql,
                id, asta.getAstaManager().getId(), asta.getConfigurazione().getId(), asta.getInfoAsta().getTipo(),
                asta.getInfoAsta().getPrezzoPartenza(), asta.getInfoAsta().getDataInizio(),
                asta.getInfoAsta().getDataFine(), asta.getInfoAsta().getDurataTimeSlot(),
                asta.getInfoAsta().isRifiutata(), asta.getInfoAsta().getCriterioTerminazione());
        return id;
    }

    @Override
    public int eliminaAsta(UUID id) {
        final String sql = "DELETE FROM asta WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<AstaModel> trovaAsta(UUID id) {
        final String sql = SELECT_ALL_FROM_ASTA + " WHERE id = ?";
        List<AstaModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                id);
        AstaModel returnable = (results.isEmpty())? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<AstaModel> trovaAste() {
        return jdbcTemplate.query(SELECT_ALL_FROM_ASTA,
                (resultSet, i) -> makeAstaFromResultSet(resultSet));
    }

    @Override
    public List<AstaModel> trovaAsteInCorso() {
        final String sql = SELECT_ALL_FROM_ASTA +
                " WHERE asta.data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet));
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoUtente(UUID idAstaManager) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " WHERE id_asta_manager = ? AND data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idAstaManager);
    }

    @Override
    public List<AstaModel> trovaAsteScaduteUtente(UUID idAstaManager) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " WHERE id_asta_manager = ? AND data_fine IS NOT NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idAstaManager);
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoOfferente(UUID idOfferente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = offerta.id_asta " +
                "WHERE id_offerente = ? AND data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoBustaChiusaOfferente(UUID idOfferente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = offerta.id_asta " +
                "WHERE tipo = busta_chiusa AND id_offerente = ? AND data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(UUID idOfferente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = offerta.id_asta " +
                "ORDER BY credito_offerto DESC LIMIT 1 " +
                "WHERE tipo = superamento_immediato AND id_offerente = ? AND data_fine IS NULL ";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(UUID idOfferente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = offerta.id_asta " +
                "ORDER BY credito_offerto DESC OFFSET 1 ROWS " +
                "WHERE tipo = superamento_immediato AND id_offerente = ? AND data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteVinteDaUtente(UUID idUtente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = offerta.id_asta " +
                "ORDER BY credito_offerto DESC LIMIT 1 " +
                "WHERE id_offerente = ? AND data_fine IS NOT NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public int aggiornaAsta(UUID id, AstaModel astaAggiornata) {
        final String sql = "UPDATE asta " +
                "SET id_asta_manager = ?, id_configurazione = ?, tipo = ?, prezzo_partenza = ?, data_inizio = ?, " +
                "data_fine = ?, durata_timeslot = ?, rifiutata = ?, criterio_terminazione = ?::tipoterminazioneasta " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql,
                astaAggiornata.getAstaManager().getId(), astaAggiornata.getConfigurazione().getId(),
                astaAggiornata.getInfoAsta().getTipo(), astaAggiornata.getInfoAsta().getPrezzoPartenza(),
                astaAggiornata.getInfoAsta().getDataInizio(), astaAggiornata.getInfoAsta().getDataFine(),
                astaAggiornata.getInfoAsta().getDurataTimeSlot(), astaAggiornata.getInfoAsta().isRifiutata(),
                astaAggiornata.getInfoAsta().getCriterioTerminazione(), id);
    }

    @Override
    public Float rinunciaAsta(UUID idAsta, UUID idUtente) {
        final String sql = "UPDATE asta " +
                "SET rifiutata = ? " +
                "WHERE id = ?";
        if(jdbcTemplate.update(sql, true, idAsta) == 0)
            return null;

        Optional<AstaModel> asta = trovaAsta(idAsta);
        if(asta.isEmpty())
            return null;
        double penale = asta.get().getConfigurazione().getPenale();

        Optional<OffertaModel> offerta = offertaDAO.trovaOffertaMaggioreAsta(idAsta);
        if(offerta.isEmpty())
            return null;
        float creditoOfferto = offerta.get().getCreditoOfferto();

        float risarcimento = (float) (penale * creditoOfferto);
        if(utenteRegistratoDAO.aggiungiCredito(idUtente, risarcimento) == 0)
            return null;
        return risarcimento;
    }

    private AstaModel makeAstaFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));

        UUID idAstaManager = UUID.fromString(resultSet.getString("id_asta_manager"));
        UtenteRegistratoModel astaManager = utenteRegistratoDAO.trovaUtenteRegistrato(idAstaManager)
                .orElse(null);

        UUID idConfigurazione = UUID.fromString(resultSet.getString("id_configurazione"));
        ConfigurazioneModel configurazione = configurazioneDAO.trovaConfigurazione(idConfigurazione)
                .orElse(null);

        List<OggettoModel> oggetti = oggettoDAO.trovaOggettiAsta(id);
        List<OffertaModel> offerte = offertaDAO.trovaOfferteAsta(id);
        String tipo = resultSet.getString("tipo");
        float prezzoPartenza = resultSet.getFloat("prezzo_partenza");
        Timestamp dataInizio = resultSet.getTimestamp("data_inizio");
        Timestamp dataFine = resultSet.getTimestamp("data_fine");
        Time durataTimeSlot = resultSet.getTime("durata_timeslot");
        boolean rifiutata = resultSet.getBoolean("rifiutata");
        String criterioTerminazione = resultSet.getString("criterio_terminazione");
        InfoAstaModel infoAsta =
                new InfoAstaModel(tipo, prezzoPartenza, dataInizio, dataFine, durataTimeSlot, rifiutata,
                        criterioTerminazione);
        return new AstaModel(id, infoAsta, configurazione, oggetti, astaManager, offerte);
    }
}
