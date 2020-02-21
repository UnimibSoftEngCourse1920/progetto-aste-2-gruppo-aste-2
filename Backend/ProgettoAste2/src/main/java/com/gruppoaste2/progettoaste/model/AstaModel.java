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
    ConfigurazioneModel configurazione;
    OfferteModel offerta[];
    //ToDo:
    //private Date durataTimeSolt; ? non li setto in ConfigurazioneModel?!


    public AstaModel(ArrayList<OggettoModel> oggetto, UUID id, double ora, Date dataInizio, Date dataFine,
                     ConfigurazioneModel configurazione, OfferteModel[] offerta) {
        this.oggetto = oggetto;
        this.id = id;
        this.ora = ora;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.configurazione = configurazione;
        this.offerta = offerta; //sta cosa mi sa di schifo assurdo, ma ehy lo ha fatto intellij ¯\_(ツ)_/¯
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