package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.ConfigurazioneDAO;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConfigurazioneService {
    private final ConfigurazioneDAO configurazioneDAO;

    @Autowired
    public ConfigurazioneService(@Qualifier("postgres-configurazione") ConfigurazioneDAO configurazioneDAO) {
        this.configurazioneDAO = configurazioneDAO;
    }

    public UUID inserisciConfigurazione(ConfigurazioneModel configurazioneModel) {
        return configurazioneDAO.inserisciConfigurazione(configurazioneModel);
    }

    public int eliminaConfiguazione(UUID idConfigurazione) {
        return configurazioneDAO.eliminaConfiguazione(idConfigurazione);
    }

    public Optional<ConfigurazioneModel> trovaConfigurazione(UUID idConfigurazione) {
        return configurazioneDAO.trovaConfigurazione(idConfigurazione);
    }

    public List<ConfigurazioneModel> trovaConfigurazioni() {
        return configurazioneDAO.trovaConfigurazioni();
    }

    public Optional<ConfigurazioneModel> trovaUltimaConfigurazione()
    {
        return configurazioneDAO.trovaUltimaConfigurazione();
    }
}
