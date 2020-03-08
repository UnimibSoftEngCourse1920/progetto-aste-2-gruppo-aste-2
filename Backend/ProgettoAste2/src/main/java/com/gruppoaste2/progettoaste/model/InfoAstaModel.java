package com.gruppoaste2.progettoaste.model;

import java.sql.Date;
import java.sql.Time;

public class InfoAstaModel {

    private final String tipo;
    private final double prezzoPartenza;
    private final Date dataInizio;
    private final Date dataFine;
    private final Time durataTimeSlot;

    public InfoAstaModel(String tipo, double prezzoPartenza, Date dataInizio, Date dataFine, Time durataTimeSlot) {
        this.tipo = tipo;
        this.prezzoPartenza = prezzoPartenza;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.durataTimeSlot = durataTimeSlot;
    }

    public String getTipo() { return tipo; }

    public double getPrezzoPartenza() {
        return prezzoPartenza;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public Time getDurataTimeSlot() {
        return durataTimeSlot;
    }
}
 