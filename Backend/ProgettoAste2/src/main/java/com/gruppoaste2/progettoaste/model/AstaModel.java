package com.gruppoaste2.progettoaste.model;

import java.util.List;
import java.util.UUID;

public class AstaModel {


    private final UUID id;
    private final ConfigurazioneModel configurazione;
    private final List<OffertaModel> offerte;
    private final List<OggettoModel> oggetti;
    private final InfoAsta infoAsta;

    public AstaModel(UUID id, ConfigurazioneModel configurazione, List<OffertaModel> offerte, List<OggettoModel> oggetti, InfoAsta infoAsta) {
        this.id = id;
        this.configurazione = configurazione;
        this.offerte = offerte;
        this.oggetti = oggetti;
        this.infoAsta = infoAsta;
    }

    public UUID getId() {
        return id;
    }

    public ConfigurazioneModel getConfigurazione() {
        return configurazione;
    }

    public List<OffertaModel> getOfferte() {
        return offerte;
    }

    public List<OggettoModel> getOggetti() {
        return oggetti;
    }

    public InfoAsta getInfoAsta() {
        return infoAsta;
    }
}
