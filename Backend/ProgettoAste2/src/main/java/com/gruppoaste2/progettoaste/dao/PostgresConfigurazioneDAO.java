package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
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

    @Autowired
    public PostgresConfigurazioneDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int inserisciConfigurazione(UUID id, ConfigurazioneModel configurazioneModel) {
        final String sql = "INSERT INTO configurazione(id,tipo_timeslot, numero_max_timeslot, numero_offerte_contemporanee_utente, penale, data_creazione, durata_timeslot_fisso)" +
                " VALUES(?,?::tipotimeslotasta,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,configurazioneModel.getTimeSlot(),configurazioneModel.getMaxTimeSlot(),configurazioneModel.getMaxOfferte(),
                configurazioneModel.getPenale(), configurazioneModel.getDataCreazione(), configurazioneModel.getDurataTimeslotFisso());
    }

    @Override
    public int eliminaConfiguazione(UUID id) {
        final String sql = "DELETE FROM configurazione WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<ConfigurazioneModel> trovaConfigurazione(UUID id) {
        final String sql = "SELECT * FROM configurazione WHERE id = ?";
        ConfigurazioneModel confTrovato = jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    String timeSlot = resultSet.getString("tipo_timeslot");
                    int  maxTimeSlot = Integer.parseInt(resultSet.getString("numero_max_timeslot"));
                    int maxOfferte = Integer.parseInt(resultSet.getString("numero_offerte_contemporanee_utente"));
                    double penale =  Double.parseDouble(resultSet.getString("penale"));
                    Date dataCreazione =  Date.valueOf(resultSet.getString("data_creazione"));
                    Time durata = Time.valueOf(resultSet.getString("durata_timeslot_fisso"));
                    return new ConfigurazioneModel(id,timeSlot,maxTimeSlot,maxOfferte,penale,dataCreazione, durata);
                },
                id);
        return  Optional.ofNullable(confTrovato);
    }

    @Override
    public Optional<List<ConfigurazioneModel>> trovaConfigurazioni() {
        final String sql = "SELECT * FROM configurazione";
        List<ConfigurazioneModel> listConfigurazioni = jdbcTemplate.query(sql, (resultSet, i) ->
        {
            String timeSlot = resultSet.getString("tipo_timeslot");
            UUID id = UUID.fromString(resultSet.getString("id"));
            int  maxTimeSlot = Integer.parseInt(resultSet.getString("numero_max_timeslot"));
            int maxOfferte = Integer.parseInt(resultSet.getString("numero_offerte_contemporanee_utente"));
            double penale =  Double.parseDouble(resultSet.getString("penale"));
            Date dataCreazione =  Date.valueOf(resultSet.getString("data_creazione"));
            Time durata = Time.valueOf(resultSet.getString("durata_timeslot_fisso"));
            return new ConfigurazioneModel(id,timeSlot,maxTimeSlot,maxOfferte,penale,dataCreazione, durata);
        });

        return Optional.ofNullable(listConfigurazioni);
    }

    @Override
    public Optional<ConfigurazioneModel> trovaUltimaConfigurazione() {
        final String sql = "SELECT * FROM configurazione ORDER BY data_creazione DESC LIMIT 1";
        ConfigurazioneModel configTrovata = jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    String timeSlot = resultSet.getString("tipo_timeslot");
                    UUID id = UUID.fromString(resultSet.getString("id"));
                    int  maxTimeSlot = Integer.parseInt(resultSet.getString("numero_max_timeslot"));
                    int maxOfferte = Integer.parseInt(resultSet.getString("numero_offerte_contemporanee_utente"));
                    double penale =  Double.parseDouble(resultSet.getString("penale"));
                    Date dataCreazione =  Date.valueOf(resultSet.getString("data_creazione"));
                    Time durata = Time.valueOf(resultSet.getString("durata_timeslot_fisso"));
                    return new ConfigurazioneModel(id,timeSlot,maxTimeSlot,maxOfferte,penale,dataCreazione, durata);
                });
        return Optional.ofNullable(configTrovata);
    }
}
