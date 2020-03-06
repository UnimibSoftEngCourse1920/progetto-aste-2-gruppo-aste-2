package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
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

    private static final String ID = "id";
    private static final String IDASTAMANAGER = "id_asta_manager";
    private static final String IDCONFIGURAZIONE = "id_configurazione";
    private static final String TIPO ="tipo";
    private static final String PREZZOPARTENZA = "prezzo_partenza";
    private static final String DATAINIZIO = "data_inizio";
    private static final String DATAFINE = "data_fine";
    private static final String DURATATIMESLOT = "durata_timeslot";

    @Autowired
    public PostgresAstaDAO(JdbcTemplate jdbcTemplate, UtenteRegistratoDAO utenteRegistratoDAO, ConfigurazioneDAO configurazioneDAO, OggettoDAO oggettoDAO, OffertaDAO offertaDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.utenteRegistratoDAO = utenteRegistratoDAO;
        this.configurazioneDAO = configurazioneDAO;
        this.oggettoDAO = oggettoDAO;
        this.offertaDAO = offertaDAO;
    }

    @Override
    public int inserisciAsta(UUID id, AstaModel asta) {
        final String sql = "INSERT INTO asta(id,id_asta_manager,id_configurazione,tipo,prezzo_partenza," +
                "data_inizio,data_fine,durata_timeslot)" +
                " VALUES(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,asta.getAstaManager().getId(),asta.getConfigurazione().getId(),asta.getInfoAsta().getTipo(),
                asta.getInfoAsta().getPrezzoPartenza(),asta.getInfoAsta().getDataInizio(),asta.getInfoAsta().getDataFine(),
                asta.getInfoAsta().getDurataTimeSlot());
    }

    @Override
    public int eliminaAsta(UUID id) {
        final String sql = "DELETE FROM asta WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<AstaModel> trovaAsta(UUID id) {
        final String sql = "SELECT * " +
                "FROM asta " +
                "JOIN tipo_asta ON asta.tipo = tipo_asta.nome " +
                "WHERE id = ?";
        AstaModel astaTrovata = jdbcTemplate.queryForObject(
                sql, new Object[]{id},
                (resultSet, i) -> {
                    UUID idTrovato = UUID.fromString(resultSet.getString(ID));

                    UUID idAstaManager = UUID.fromString(resultSet.getString(IDASTAMANAGER));
                    Optional<UtenteRegistratoModel> optionalAstaManager = utenteRegistratoDAO.trovaUtenteRegistrato(idAstaManager);
                    UtenteRegistratoModel astaManager;
                    if(optionalAstaManager.isPresent())
                        astaManager = optionalAstaManager.get();
                    else
                        return null;
                    // TODO: throw AstaManagerNotFoundException

                    UUID idConfigurazione = UUID.fromString(resultSet.getString(IDCONFIGURAZIONE));
                    Optional<ConfigurazioneModel> optionalConfigurazione = configurazioneDAO.trovaConfigurazione(idConfigurazione);
                    ConfigurazioneModel configurazione;
                    if(optionalConfigurazione.isPresent())
                        configurazione = optionalConfigurazione.get();
                    else
                        return null;
                    // TODO: throw ConfigurazioneNotFoundException

                    List<OggettoModel> oggetti = oggettoDAO.trovaOggetti(id);
                    List<OffertaModel> offerte = offertaDAO.trovaTutteOfferteAsta(id);
                    String tipo = resultSet.getString(TIPO);
                    float prezzoPartenza = resultSet.getFloat(PREZZOPARTENZA);
                    Date dataInizio = resultSet.getDate(DATAINIZIO);
                    Date dataFine = resultSet.getDate(DATAFINE);
                    Time durataTimeslot = resultSet.getTime(DURATATIMESLOT);
                    return new AstaModel(idTrovato, new InfoAsta(tipo, prezzoPartenza, dataInizio, dataFine, durataTimeslot),
                            configurazione, oggetti, astaManager, offerte);
                });
        return Optional.ofNullable(astaTrovata);
    }

    @Override
    public List<AstaModel> trovaTutteAste() {
        final String sql = "SELECT * FROM asta";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString(ID));

            UUID idAstaManager = UUID.fromString(resultSet.getString(IDASTAMANAGER));
            Optional<UtenteRegistratoModel> optionalAstaManager = utenteRegistratoDAO.trovaUtenteRegistrato(idAstaManager);
            UtenteRegistratoModel astaManager;
            if(optionalAstaManager.isPresent())
                astaManager = optionalAstaManager.get();
            else
                return null;
            // TODO: throw AstaManagerNotFoundException

            UUID idConfigurazione = UUID.fromString(resultSet.getString(IDCONFIGURAZIONE));
            Optional<ConfigurazioneModel> optionalConfigurazione = configurazioneDAO.trovaConfigurazione(idConfigurazione);
            ConfigurazioneModel configurazione;
            if(optionalConfigurazione.isPresent())
                configurazione = optionalConfigurazione.get();
            else
                return null;
            // TODO: throw AstaManagerNotFoundException
            List<OggettoModel> oggetti = oggettoDAO.trovaOggetti(id);
            List<OffertaModel> offerte = offertaDAO.trovaTutteOfferteAsta(id);
            String tipo = resultSet.getString(TIPO);
            float prezzoPartenza = resultSet.getFloat(PREZZOPARTENZA);
            Date dataInizio = resultSet.getDate(DATAINIZIO);
            Date dataFine = resultSet.getDate(DATAFINE);
            Time durataTimeslot = resultSet.getTime(DURATATIMESLOT);
            return new AstaModel(id, new InfoAsta(tipo, prezzoPartenza, dataInizio, dataFine, durataTimeslot),
                    configurazione, oggetti, astaManager, offerte);
        });
    }

    @Override
    public int aggiornaAsta(UUID id, AstaModel astaAggiornata) {
        final String sql = "UPDATE asta SET id_asta_manager = ?, id_configurazione = ?, tipo = ?, prezzoPartenza = ?, " +
                "data_inizio = ?, data_fine = ?, durata_timeslot = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, astaAggiornata.getAstaManager().getId(), astaAggiornata.getConfigurazione().getId(),
                astaAggiornata.getInfoAsta().getTipo(), astaAggiornata.getInfoAsta().getPrezzoPartenza(),
                astaAggiornata.getInfoAsta().getDataInizio(), astaAggiornata.getInfoAsta().getDataFine(),
                astaAggiornata.getInfoAsta().getDurataTimeSlot(), id);
    }
}
