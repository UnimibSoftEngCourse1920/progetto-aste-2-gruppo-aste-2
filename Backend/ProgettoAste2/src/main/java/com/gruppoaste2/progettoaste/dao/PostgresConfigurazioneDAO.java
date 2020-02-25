package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository("postgres-configurazione")
public class PostgresConfigurazioneDAO implements ConfigurazioneDAO{
    @Override
    public boolean inserisciConfigurazione(ConfigurazioneModel configurazioneModel) {
        return false;
    }

    @Override
    public boolean eliminaConfiguazione(UUID id) {
        return false;
    }

    @Override
    public ConfigurazioneModel trovaConfigurazione(UUID id) {
        return null;
    }

    @Override
    public List<ConfigurazioneModel> trovaConfigurazioni() {
        return Collections.emptyList();
    }
}
