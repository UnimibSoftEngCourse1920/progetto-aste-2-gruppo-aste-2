package com.gruppoaste2.progettoaste.model;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;


public class ConfigurazioneModel {

    private final UUID id;
    private final String timeSlot;
    private final int maxTimeSlot;
    private final int maxOfferte;
    private final double penale; // 0.1 per indicare 10% per esempio valore tra 0 e 1
    private final Date dataCreazione;
    private final Time durataTimeslotFisso;

    public ConfigurazioneModel(UUID id, String timeSlot, int maxTimeSlot, int maxOfferte, double penale, Date dataCreazione, Time durataTimeslotFisso) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.maxTimeSlot = maxTimeSlot;
        this.maxOfferte = maxOfferte;
        this.penale = penale;
        this.dataCreazione = dataCreazione;
        this.durataTimeslotFisso = durataTimeslotFisso;

    }

    public UUID getId() {
        return id;
    }

    public String getTimeSlot() {
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

    public Time getDurataTimeslotFisso() {
        return durataTimeslotFisso;
    }
}
