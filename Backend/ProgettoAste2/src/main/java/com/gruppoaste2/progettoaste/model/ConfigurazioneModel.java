package com.gruppoaste2.progettoaste.model;

import java.sql.Date;
import java.util.UUID;


public class ConfigurazioneModel {

    private final UUID id;
    private final boolean timeSlot;
    private final int maxTimeSlot;
    private final int maxOfferte;
    private final double penale; // 0.1 per indicare 10% per esempio valore tra 0 e 1
    private final Date dataCreazione;

    public ConfigurazioneModel(UUID id, boolean timeSlot, int maxTimeSlot, int maxOfferte, double penale, Date dataCreazione) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.maxTimeSlot = maxTimeSlot;
        this.maxOfferte = maxOfferte;
        this.penale = penale;
        this.dataCreazione = dataCreazione;
    }

    public UUID getId() {
        return id;
    }

    public boolean isTimeSlot() {
        return timeSlot;
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
}
