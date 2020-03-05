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

    int inserisciConfigurazione(UUID id, ConfigurazioneModel configurazioneModel);

    int eliminaConfiguazione(UUID id);

    Optional<ConfigurazioneModel> trovaConfigurazione(UUID id);

    Optional<List<ConfigurazioneModel>> trovaConfigurazioni();

    Optional<ConfigurazioneModel> trovaUltimaConfigurazione();

}
