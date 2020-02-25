package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;

import java.util.List;
import java.util.UUID;

public interface ConfigurazioneDAO {

    public boolean inserisciConfigurazione(ConfigurazioneModel configurazioneModel);

    public boolean eliminaConfiguazione(UUID id);

    public ConfigurazioneModel trovaConfigurazione(UUID id);

    public List<ConfigurazioneModel> trovaConfigurazioni();

}
