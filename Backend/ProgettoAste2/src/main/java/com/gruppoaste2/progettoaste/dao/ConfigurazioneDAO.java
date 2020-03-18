package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConfigurazioneDAO {

    UUID inserisciConfigurazione(UUID idConfigurazione, ConfigurazioneModel configurazione);

    default UUID inserisciConfigurazione(ConfigurazioneModel configurazione)
    {
        UUID idConfigurazione = UUID.randomUUID();
        return inserisciConfigurazione(idConfigurazione, configurazione);
    }

    int eliminaConfiguazione(UUID idConfigurazione);

    Optional<ConfigurazioneModel> trovaConfigurazione(UUID idConfigurazione);

    List<ConfigurazioneModel> trovaConfigurazioni();

    Optional<ConfigurazioneModel> trovaUltimaConfigurazione();
}
