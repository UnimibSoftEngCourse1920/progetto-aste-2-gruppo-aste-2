package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConfigurazioneDAO {

    default int inserisciConfigurazione(ConfigurazioneModel configurazioneModel)
    {
        UUID id = UUID.randomUUID();
        return inserisciConfigurazione(id,configurazioneModel);
    }

    public int inserisciConfigurazione(UUID id, ConfigurazioneModel configurazioneModel);

    public int eliminaConfiguazione(UUID id);

    public Optional<ConfigurazioneModel> trovaConfigurazione(UUID id);

    public Optional<List<ConfigurazioneModel>> trovaConfigurazioni();

    public Optional<ConfigurazioneModel> trovaUltimaConfigurazione();

}
