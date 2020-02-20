package com.gruppoaste2.progettoaste.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class AstaModel {

    private ArrayList <OggettoModel> oggetto; //arraylist?
    private final UUID id;
    private final double ora;
    private final Date dataInizio;
    private final Date dataFine;
    //ToDo:
    //private Date durataTimeSolt; ?

    public AstaModel(UUID id, double ora, Date dataInizio, Date dataFine /*, Date durataTimeSolt*/){
        this.id = id;
        this.ora = ora;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        /*this.durataTimeSlot = durataTimeSlot;*/
    }

    public ArrayList<OggettoModel> getOggetto() {
        return oggetto;
    }

    public UUID getId() {
        return id;
    }

    public double getOra() {
        return ora;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }
}