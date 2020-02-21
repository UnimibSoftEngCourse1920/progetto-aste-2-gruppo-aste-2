package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class ConfigurazioneModel {
    private UUID id;
    private boolean timeSlot;
    //Time durataTimeslot ?
    private int maxTimeSlot;
    private int maxOfferte;
    private double penale; //dovrà essere percentuale

    public ConfigurazioneModel(UUID id, boolean timeSlot, int maxTimeSlot, int maxOfferte, double penale) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.maxTimeSlot = maxTimeSlot;
        this.maxOfferte = maxOfferte;
        this.penale = penale;
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
}
