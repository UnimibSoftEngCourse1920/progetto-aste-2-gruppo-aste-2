package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
        final String sql = "INSERT INTO configurazione(id,timeSlot,maxTimeSlot,maxOfferte, penale, dataCreazione)" +
                " VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                id,configurazioneModel.isTimeSlot(),configurazioneModel.getMaxTimeSlot(),configurazioneModel.getMaxOfferte(),
                configurazioneModel.getPenale(), configurazioneModel.getDataCreazione());
    }

    @Override
    public int eliminaConfiguazione(UUID id) {
        final String sql = "DELETE FROM configurazione WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<ConfigurazioneModel> trovaConfigurazione(UUID id) {
        final String sql = "SELECT * FROM amministratore WHERE id = ?";
        ConfigurazioneModel confTrovato = jdbcTemplate.queryForObject(sql,
                (resultSet, i) -> {
                    boolean timeSlot = Boolean.parseBoolean(resultSet.getString("timeSlot"));
                    int  maxTimeSlot = Integer.parseInt(resultSet.getString("maxTimeSlot"));
                    int maxOfferte = Integer.parseInt(resultSet.getString("maxOfferte"));
                    double penale =  Double.parseDouble(resultSet.getString("penale"));
                    Date dataCreazione =  Date.valueOf(resultSet.getString("dataCreazione"));
                    return new ConfigurazioneModel(id,timeSlot,maxTimeSlot,maxOfferte,penale,dataCreazione);
                },
                id);
        return  Optional.ofNullable(confTrovato);
    }

    @Override
    public Optional<List<ConfigurazioneModel>> trovaConfigurazioni() {
        final String sql = "SELECT * FROM configurazione";
        List<ConfigurazioneModel> listConfigurazioni = jdbcTemplate.query(sql, (resultSet, i) ->
        {
            UUID id = UUID.fromString(resultSet.getString("id"));
            boolean timeSlot = Boolean.parseBoolean(resultSet.getString("timeSlot"));
            int maxTimeSlot = Integer.parseInt(resultSet.getString("maxTimeSlot"));
            int maxOfferte = Integer.parseInt(resultSet.getString("maxOfferte"));
            double penale = Double.parseDouble(resultSet.getString("penale"));
            Date dataCreazione = Date.valueOf(resultSet.getString("dataCreazione"));
            return new ConfigurazioneModel(id, timeSlot, maxTimeSlot, maxOfferte, penale, dataCreazione);
        });

        return Optional.ofNullable(listConfigurazioni);
    }

    public boolean esisteConfSimile (ConfigurazioneModel configurazioneModel){
        final String sql = "SELECT EXISTS(SELECT 1 FROM configurazione WHERE timeSlot = ? AND maxTimeSlot = ?" +
                "AND maxOfferte = ? AND penale = ? AND dataCreazione = ?)";
        return jdbcTemplate.queryForObject(sql,Boolean.class, configurazioneModel.isTimeSlot(), configurazioneModel.getMaxTimeSlot(),
                configurazioneModel.getMaxOfferte(), configurazioneModel.getPenale(), configurazioneModel.getDataCreazione());
    }
}
