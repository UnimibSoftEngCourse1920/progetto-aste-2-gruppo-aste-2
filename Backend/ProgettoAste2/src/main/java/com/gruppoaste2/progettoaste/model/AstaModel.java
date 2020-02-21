package com.gruppoaste2.progettoaste.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AstaModel {

    private List<OggettoModel> oggetti;
    private final UUID id;
    private final double prezzoPartenza;
    private final Date dataInizio;
    private final Date dataFine;
    private final ConfigurazioneModel configurazione;
    private final List<OffertaModel> offerte;
    private final Time durataTimeSlot;

    public AstaModel(List<OggettoModel> oggetti, UUID id, double prezzoPartenza, Date dataInizio, Date dataFine, ConfigurazioneModel configurazione, List<OffertaModel> offerte, Time durataTimeSlot) {
        this.oggetti = oggetti;
        this.id = id;
        this.prezzoPartenza = prezzoPartenza;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.configurazione = configurazione;
        this.offerte = offerte;
        this.durataTimeSlot = durataTimeSlot;
    }

    public List<OggettoModel> getOggetti() {
        return oggetti;
    }

    public UUID getId() {
        return id;
    }

    public double getPrezzoPartenza() {
        return prezzoPartenza;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public ConfigurazioneModel getConfigurazione() {
        return configurazione;
    }

    public List<OffertaModel> getOfferte() {
        return offerte;
    }

    public Time getDurataTimeSlot() {
        return durataTimeSlot;
    }
}
