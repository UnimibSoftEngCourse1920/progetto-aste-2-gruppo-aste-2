package com.gruppoaste2.progettoaste.model;

import java.util.List;
import java.util.UUID;

public class AstaModel {

    private final UUID id;
    private final InfoAstaModel infoAsta;
    private final ConfigurazioneModel configurazione;
    private final List<OggettoModel> oggetti;
    private final UtenteRegistratoModel astaManager;
    private final List<OffertaModel> offerte;

    public AstaModel(UUID id, InfoAstaModel infoAsta, ConfigurazioneModel configurazione, List<OggettoModel> oggetti,
                     UtenteRegistratoModel astaManager, List<OffertaModel> offerte) {
        this.id = id;
        this.infoAsta = infoAsta;
        this.configurazione = configurazione;
        this.oggetti = oggetti;
        this.astaManager = astaManager;
        this.offerte = offerte;
    }

    public UUID getId() {
        return id;
    }

    public InfoAstaModel getInfoAsta() {
        return infoAsta;
    }

    public ConfigurazioneModel getConfigurazione() {
        return configurazione;
    }

    public List<OggettoModel> getOggetti() {
        return oggetti;
    }

    public UtenteRegistratoModel getAstaManager() { return astaManager; }

    public List<OffertaModel> getOfferte() {
        return offerte;
    }
}
