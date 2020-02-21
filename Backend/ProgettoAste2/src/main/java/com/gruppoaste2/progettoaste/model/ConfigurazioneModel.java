package com.gruppoaste2.progettoaste.model;

import java.util.UUID;


public class ConfigurazioneModel {

    private final UUID id;
    private final boolean timeSlot;
    private final int maxTimeSlot;
    private final int maxOfferte;
    private final double penale; // 0.1 per indicare 10% per esempio

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
