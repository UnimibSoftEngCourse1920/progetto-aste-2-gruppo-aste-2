package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConfigurazioneDAO {

    int inserisciConfigurazione(UUID id, ConfigurazioneModel configurazione);

    default int inserisciConfigurazione(ConfigurazioneModel configurazione)
    {
        UUID id = UUID.randomUUID();
        return inserisciConfigurazione(id, configurazione);
    }

    int eliminaConfiguazione(UUID id);

    Optional<ConfigurazioneModel> trovaConfigurazione(UUID id);

    List<ConfigurazioneModel> trovaConfigurazioni();

    Optional<ConfigurazioneModel> trovaUltimaConfigurazione();
}
