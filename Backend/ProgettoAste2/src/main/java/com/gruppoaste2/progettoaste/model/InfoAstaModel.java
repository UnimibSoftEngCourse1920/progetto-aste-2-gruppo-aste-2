package com.gruppoaste2.progettoaste.model;

import java.sql.Time;
import java.sql.Timestamp;

public class InfoAstaModel {

    private final String tipo;
    private final double prezzoPartenza;
    private final Timestamp dataInizio;
    private final Timestamp dataFine;
    private final Time durataTimeSlot;
    private final boolean rifiutata;

    public InfoAstaModel(String tipo, double prezzoPartenza, Timestamp dataInizio, Timestamp dataFine,
                         Time durataTimeSlot, boolean rifiutata) {
        this.tipo = tipo;
        this.prezzoPartenza = prezzoPartenza;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.durataTimeSlot = durataTimeSlot;
        this.rifiutata = rifiutata;
    }

    public String getTipo() { return tipo; }

    public double getPrezzoPartenza() {
        return prezzoPartenza;
    }

    public Timestamp getDataInizio() {
        return dataInizio;
    }

    public Timestamp getDataFine() {
        return dataFine;
    }

    public Time getDurataTimeSlot() {
        return durataTimeSlot;
    }

    public boolean isRifiutata() {
        return rifiutata;
    }
}
 