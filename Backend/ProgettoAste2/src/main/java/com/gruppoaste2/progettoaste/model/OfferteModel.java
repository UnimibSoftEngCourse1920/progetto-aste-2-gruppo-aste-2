package com.gruppoaste2.progettoaste.model;

import java.util.Date;

public class OfferteModel {
    private double creditoOffreto; //da mettere private nel digramma
    private Date data; //da mettere private nel digramma
    AstaModel offerte;

    public OfferteModel(double creditoOfferto, Date data){
        this.creditoOffreto = creditoOfferto;
        this.data = data;
    }

    public double getCreditoOffreto() {
        return creditoOffreto;
    }

    public Date getData() {
        return data;
    }
}
