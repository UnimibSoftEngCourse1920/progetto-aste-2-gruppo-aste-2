package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Repository("postgres-configurazione")
public class PostgresConfigurazioneDAO implements ConfigurazioneDAO{

    private final JdbcTemplate jdbcTemplate;
    private static final String TIMESLOT = "tipo_timeslot";
    private static final String NUMERO_OFFERTE_CONTEMPORANEE_UTENTE = "numero_offerte_contemporanee_utente";
    private static final String DURATA_TIMESLOT_FISSO = "durata_timeslot_fisso";
    private static final String DATA_CREAZIONE = "data_creazione";
    private static final String PENALE = "penale";

    @Autowired
    public PostgresConfigurazioneDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int inserisciConfigurazione(UUID id, ConfigurazioneModel configurazioneModel) {
        final String sql = "INSERT INTO configurazione(id,tipo_timeslot, numero_max_timeslot, numero_offerte_contemporanee_utente, penale, data_creazione, durata_timeslot_fisso)" +
                " VALUES(?,?::tipotimeslotasta,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,configurazioneModel.getTipoTimeSlot(),configurazioneModel.getMaxTimeSlot(),configurazioneModel.getMaxOfferte(),
                configurazioneModel.getPenale(), configurazioneModel.getDataCreazione(), configurazioneModel.getDurataTimeSlotFisso());
    }

    @Override
    public int eliminaConfiguazione(UUID id) {
        final String sql = "DELETE FROM configurazione WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<ConfigurazioneModel> trovaConfigurazione(UUID id) {
        final String sql = "SELECT * FROM configurazione WHERE id = ?";
        List<ConfigurazioneModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    String timeSlot = resultSet.getString(TIMESLOT);
                    int  maxTimeSlot = Integer.parseInt(resultSet.getString(NUMERO_OFFERTE_CONTEMPORANEE_UTENTE));
                    int maxOfferte = Integer.parseInt(resultSet.getString(NUMERO_OFFERTE_CONTEMPORANEE_UTENTE));
                    double penale =  Double.parseDouble(resultSet.getString(PENALE));
                    Date dataCreazione =  Date.valueOf(resultSet.getString(DATA_CREAZIONE));
                    long durata = resultSet.getLong(DURATA_TIMESLOT_FISSO);
                    return new ConfigurazioneModel(id,timeSlot,maxTimeSlot,maxOfferte,penale,dataCreazione, durata);
                },
                id);

        ConfigurazioneModel returnable = (results.isEmpty())? null : results.get(0);
        return  Optional.ofNullable(returnable);
    }

    @Override
    public Optional<List<ConfigurazioneModel>> trovaConfigurazioni() {
        final String sql = "SELECT * FROM configurazione";
        List<ConfigurazioneModel> listConfigurazioni = jdbcTemplate.query(sql, (resultSet, i) ->
        {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String timeSlot = resultSet.getString(TIMESLOT);
            int  maxTimeSlot = Integer.parseInt(resultSet.getString(NUMERO_OFFERTE_CONTEMPORANEE_UTENTE));
            int maxOfferte = Integer.parseInt(resultSet.getString(NUMERO_OFFERTE_CONTEMPORANEE_UTENTE));
            double penale =  Double.parseDouble(resultSet.getString(PENALE));
            Date dataCreazione =  Date.valueOf(resultSet.getString(DATA_CREAZIONE));
            long durata = resultSet.getLong(DURATA_TIMESLOT_FISSO);
            return new ConfigurazioneModel(id,timeSlot,maxTimeSlot,maxOfferte,penale,dataCreazione, durata);
        });

        return Optional.ofNullable(listConfigurazioni);
    }

    @Override
    public Optional<ConfigurazioneModel> trovaUltimaConfigurazione() {
        final String sql = "SELECT * FROM configurazione ORDER BY data_creazione DESC LIMIT 1";
        List<ConfigurazioneModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> {
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    String timeSlot = resultSet.getString(TIMESLOT);
                    int  maxTimeSlot = Integer.parseInt(resultSet.getString(NUMERO_OFFERTE_CONTEMPORANEE_UTENTE));
                    int maxOfferte = Integer.parseInt(resultSet.getString(NUMERO_OFFERTE_CONTEMPORANEE_UTENTE));
                    double penale =  Double.parseDouble(resultSet.getString(PENALE));
                    Date dataCreazione =  Date.valueOf(resultSet.getString(DATA_CREAZIONE));
                    long durata = resultSet.getLong(DURATA_TIMESLOT_FISSO);
                    return new ConfigurazioneModel(id,timeSlot,maxTimeSlot,maxOfferte,penale,dataCreazione, durata);
                });

        ConfigurazioneModel returnable = (results.isEmpty())? null : results.get(0);
        return  Optional.ofNullable(returnable);
    }
}
