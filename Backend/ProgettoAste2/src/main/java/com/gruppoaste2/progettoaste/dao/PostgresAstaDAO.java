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
    private final String JOIN_OFFERTA = " JOIN offerta AS o ON asta.id = o.id_asta ";

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
    public UUID aggiungiAsta(UUID idAsta, AstaModel asta) {
        Time durataTimeSlot = (asta.getConfigurazione().getTipoTimeSlot().equals("fisso")) ?
                asta.getConfigurazione().getDurataTimeSlotFisso() : asta.getInfoAsta().getDurataTimeSlot();

        final String sql = "INSERT INTO asta(id, id_asta_manager, id_configurazione, tipo, prezzo_partenza, " +
                "data_inizio, data_fine, durata_timeslot, rifiutata, criterio_terminazione) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?::tipoterminazioneasta)";
        if(jdbcTemplate.update(sql,
                idAsta, asta.getAstaManager().getId(), asta.getConfigurazione().getId(), asta.getInfoAsta().getTipo(),
                asta.getInfoAsta().getPrezzoPartenza(), asta.getInfoAsta().getDataInizio(),
                asta.getInfoAsta().getDataFine(), durataTimeSlot, asta.getInfoAsta().isRifiutata(),
                asta.getInfoAsta().getCriterioTerminazione())
                == 0)
            return null;

        List<OggettoModel> oggetti = asta.getOggetti();
        if(oggetti != null)
            for(OggettoModel oggetto : asta.getOggetti())
                if(oggettoDAO.inserisciOggetto(idAsta, oggetto) == null)
                    return null;

        return idAsta;
    }

    @Override
    public int eliminaAsta(UUID idAsta) {
        Optional<AstaModel> asta = trovaAsta(idAsta);
        if(asta.isEmpty())
            return 0;

        List<OggettoModel> oggetti = asta.get().getOggetti();
        if(oggetti != null)
            for(OggettoModel oggetto : oggetti)
                if (oggettoDAO.eliminaOggetto(oggetto.getId()) == 0)
                    return 0;

        List<OffertaModel> offerte = asta.get().getOfferte();
        if(offerte != null)
            for(OffertaModel offerta : offerte)
                if(offertaDAO.eliminaOfferta(offerta.getId()) == 0)
                    return 0;

        final String sql = "DELETE FROM asta WHERE id = ?";
        return jdbcTemplate.update(sql, idAsta);
    }

    @Override
    public Optional<AstaModel> trovaAsta(UUID idAsta) {
        final String sql = SELECT_ALL_FROM_ASTA + " WHERE id = ?";
        List<AstaModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idAsta);
        AstaModel returnable = (results.isEmpty()) ? null : results.get(0);
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
                JOIN_OFFERTA +
                "WHERE id_offerente = ? AND data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoBustaChiusaOfferente(UUID idOfferente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                JOIN_OFFERTA +
                "WHERE tipo = ? AND id_offerente = ? AND data_fine IS NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                "busta_chiusa", idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(UUID idOfferente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                JOIN_OFFERTA +
                "WHERE tipo = ? AND o.id_offerente = ? AND data_fine IS NULL " +
                "AND o.credito_offerto = " +
                "(SELECT MAX(o2.credito_offerto) " +
                "FROM offerta AS o2 " +
                "WHERE o.id = o2.id)";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                "superamento_immediato", idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(UUID idOfferente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                JOIN_OFFERTA +
                "WHERE tipo = ? AND o.id_offerente = ? AND data_fine IS NULL " +
                "AND o.credito_offerto < " +
                "(SELECT MAX(o2.credito_offerto) " +
                "FROM offerta AS o2 " +
                "WHERE o.id = o2.id)";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                "superamento_immediato", idOfferente);
    }

    @Override
    public List<AstaModel> trovaAsteVinteDaUtente(UUID idUtente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = id_asta " +
                "WHERE id_offerente = ? AND data_fine IS NOT NULL";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public List<AstaModel> trovaAsteAccettateDaUtente(UUID idUtente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = id_asta " +
                "WHERE id_offerente = ? AND data_fine IS NOT NULL AND rifiutata = false";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public List<AstaModel> trovaAsteRifiutateDaUtente(UUID idUtente) {
        final String sql = SELECT_ALL_FROM_ASTA +
                " JOIN offerta ON asta.id = id_asta " +
                "WHERE id_offerente = ? AND data_fine IS NOT NULL AND rifiutata = true";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeAstaFromResultSet(resultSet),
                idUtente);
    }

    @Override
    public int aggiornaAsta(UUID idAsta, AstaModel astaAggiornata) {
        final String sql = "UPDATE asta " +
                "SET id_asta_manager = ?, id_configurazione = ?, tipo = ?, prezzo_partenza = ?, data_inizio = ?, " +
                "data_fine = ?, durata_timeslot = ?, rifiutata = ?, criterio_terminazione = ?::tipoterminazioneasta " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql,
                astaAggiornata.getAstaManager().getId(), astaAggiornata.getConfigurazione().getId(),
                astaAggiornata.getInfoAsta().getTipo(), astaAggiornata.getInfoAsta().getPrezzoPartenza(),
                astaAggiornata.getInfoAsta().getDataInizio(), astaAggiornata.getInfoAsta().getDataFine(),
                astaAggiornata.getInfoAsta().getDurataTimeSlot(), astaAggiornata.getInfoAsta().isRifiutata(),
                astaAggiornata.getInfoAsta().getCriterioTerminazione(), idAsta);
    }

    @Override
    public int iniziaAsta(UUID idAsta) {
        final String sql = "UPDATE asta SET data_inizio = ? WHERE id = ?";
        return jdbcTemplate.update(sql, new Timestamp(System.currentTimeMillis()), idAsta);
    }

    @Override
    public UUID partecipaAdAsta(UUID idAsta, OffertaModel offerta) {
        UUID idOfferta = offertaDAO.aggiungiOfferta(idAsta, offerta);
        if(idOfferta == null)
            return null;

        if(offertaDAO.trovaOfferteAsta(idAsta).size() == 1)
            if(iniziaAsta(idAsta) == 0)
                return null;

        return idOfferta;
    }

    @Override
    public int chiudiAsta(UUID idAsta) {
        final String sql = "UPDATE asta SET data_fine = ? WHERE id = ?";
        return jdbcTemplate.update(sql, new Timestamp(System.currentTimeMillis()), idAsta);
    }

    @Override
    public Float accettaAstaVinta(UUID idAsta, UUID idVincitore) {
        Optional<AstaModel> asta = trovaAsta(idAsta);
        if(asta.isEmpty())
            return null;
        UUID idAstaManager = asta.get().getAstaManager().getId();

        Optional<OffertaModel> offerta = offertaDAO.trovaOffertaMaggioreAsta(idAsta);
        if(offerta.isEmpty())
            return null;
        float creditoOfferto = offerta.get().getCreditoOfferto();

        if(utenteRegistratoDAO.aggiungiCredito(idVincitore, - creditoOfferto) == 0)
            return null;

        if(utenteRegistratoDAO.aggiungiCredito(idAstaManager, creditoOfferto) == 0)
            return null;

        return creditoOfferto;
    }

    @Override
    public Float rinunciaAstaVinta(UUID idAsta, UUID idVincitore) {
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

        float creditoPagamentoPenale = (float) (penale * creditoOfferto);
        if(utenteRegistratoDAO.aggiungiCredito(idVincitore, - creditoPagamentoPenale) == 0)
            return null;
        return creditoPagamentoPenale;
    }

    private AstaModel makeAstaFromResultSet(ResultSet resultSet) throws SQLException {
        UUID idAsta = UUID.fromString(resultSet.getString("id"));

        UUID idAstaManager = UUID.fromString(resultSet.getString("id_asta_manager"));
        UtenteRegistratoModel astaManager = utenteRegistratoDAO.trovaUtenteRegistrato(idAstaManager)
                .orElse(null);

        UUID idConfigurazione = UUID.fromString(resultSet.getString("id_configurazione"));
        ConfigurazioneModel configurazione = configurazioneDAO.trovaConfigurazione(idConfigurazione)
                .orElse(null);

        List<OggettoModel> oggetti = oggettoDAO.trovaOggettiAsta(idAsta);
        List<OffertaModel> offerte = offertaDAO.trovaOfferteAsta(idAsta);
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
        return new AstaModel(idAsta, infoAsta, configurazione, oggetti, astaManager, offerte);
    }
}
