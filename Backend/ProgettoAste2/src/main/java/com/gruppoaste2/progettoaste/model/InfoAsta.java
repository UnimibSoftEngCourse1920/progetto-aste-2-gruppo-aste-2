package com.gruppoaste2.progettoaste.model;

import java.sql.Date;
import java.sql.Time;

public class InfoAsta {


    private final double prezzoPartenza;
    private final Date dataInizio;
    private final Date dataFine;
    private final Time durataTimeSlot;

    public InfoAsta(double prezzoPartenza, Date dataInizio, Date dataFine, Time durataTimeSlot) {
        this.prezzoPartenza = prezzoPartenza;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.durataTimeSlot = durataTimeSlot;
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

    public Time getDurataTimeSlot() {
        return durataTimeSlot;
    }
}
