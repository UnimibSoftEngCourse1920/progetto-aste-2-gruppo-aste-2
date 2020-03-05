package com.gruppoaste2.progettoaste.model;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;


public class ConfigurazioneModel {

    private final UUID id;
    private final String tipoTimeSlot;
    private final int maxTimeSlot;
    private final int maxOfferte;
    private final double penale; // 0.1 per indicare 10% per esempio valore tra 0 e 1
    private final Date dataCreazione;
    private final Time durataTimeSlotFisso;

    public ConfigurazioneModel(UUID id, String tipoTimeSlot, int maxTimeSlot, int maxOfferte, double penale, Date dataCreazione, Time durataTimeSlotFisso) {
        this.id = id;
        this.tipoTimeSlot = tipoTimeSlot;
        this.maxTimeSlot = maxTimeSlot;
        this.maxOfferte = maxOfferte;
        this.penale = penale;
        this.dataCreazione = dataCreazione;
        this.durataTimeSlotFisso = durataTimeSlotFisso;

    }

    public UUID getId() {
        return id;
    }

    public String getTipoTimeSlot() {
        return tipoTimeSlot;
    }

    public int getMaxTimeSlot() {
        return maxTimeSlot;
    }

    public int getMaxOfferte() {
        return maxOfferte;
    }

    public double getPenale() {
        return penale;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public Time getDurataTimeSlotFisso() {
        return durataTimeSlotFisso;
    }
}
