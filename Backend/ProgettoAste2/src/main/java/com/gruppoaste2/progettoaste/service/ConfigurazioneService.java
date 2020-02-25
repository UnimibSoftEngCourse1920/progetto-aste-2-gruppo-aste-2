package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.ConfigurazioneDAO;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConfigurazioneService {
    private final ConfigurazioneDAO configurazioneDAO;

    @Autowired
    public ConfigurazioneService(@Qualifier("postgres-configurazione") ConfigurazioneDAO configurazioneDAO) {
        this.configurazioneDAO = configurazioneDAO;
    }

    public boolean inserisciConfigurazione(ConfigurazioneModel configurazioneModel) {
        return configurazioneDAO.inserisciConfigurazione(configurazioneModel);
    }

    public boolean eliminaConfiguazione(UUID id) {
        return configurazioneDAO.eliminaConfiguazione(id);
    }

    public ConfigurazioneModel trovaConfigurazione(UUID id) {
        return configurazioneDAO.trovaConfigurazione(id);
    }

    public List<ConfigurazioneModel> trovaConfigurazioni() {
        return configurazioneDAO.trovaConfigurazioni();
    }
}
