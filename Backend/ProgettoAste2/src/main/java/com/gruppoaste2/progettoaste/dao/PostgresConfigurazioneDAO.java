package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
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
        final String sql = "INSERT INTO configurazione(id, tipo_timeslot, numero_max_timeslot, " +
                "numero_offerte_contemporanee_utente, penale, data_creazione, durata_timeslot_fisso) " +
                "VALUES(?, ?::tipotimeslotasta, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                id, configurazioneModel.getTipoTimeSlot(), configurazioneModel.getMaxTimeSlot(),
                configurazioneModel.getMaxOfferte(), configurazioneModel.getPenale(),
                configurazioneModel.getDataCreazione(), configurazioneModel.getDurataTimeSlotFisso());
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
                (resultSet, i) -> makeConfigurazioneFromResultSet(resultSet),
                id);
        ConfigurazioneModel returnable = (results.isEmpty())? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    @Override
    public List<ConfigurazioneModel> trovaConfigurazioni() {
        final String sql = "SELECT * FROM configurazione";
        return jdbcTemplate.query(sql,
                (resultSet, i) -> makeConfigurazioneFromResultSet(resultSet));
    }

    @Override
    public Optional<ConfigurazioneModel> trovaUltimaConfigurazione() {
        final String sql = "SELECT * FROM configurazione ORDER BY data_creazione DESC LIMIT 1";
        List<ConfigurazioneModel> results = jdbcTemplate.query(sql,
                (resultSet, i) -> makeConfigurazioneFromResultSet(resultSet));
        ConfigurazioneModel returnable = (results.isEmpty())? null : results.get(0);
        return Optional.ofNullable(returnable);
    }

    private ConfigurazioneModel makeConfigurazioneFromResultSet(ResultSet resultSet) throws SQLException {
        UUID id = UUID.fromString(resultSet.getString("id"));
        String tipoTimeSlot = resultSet.getString("tipo_timeslot");
        int maxTimeSlot = resultSet.getInt("numero_max_timeslot");
        int maxOfferte = resultSet.getInt("numero_offerte_contemporanee_utente");
        double penale =  resultSet.getDouble("penale");
        Timestamp dataCreazione =  resultSet.getTimestamp("data_creazione");
        Time durataTimeSlotFisso = resultSet.getTime("durata_timeslot_fisso");
        return new ConfigurazioneModel(id, tipoTimeSlot, maxTimeSlot, maxOfferte, penale, dataCreazione,
                durataTimeSlotFisso);
    }
}
