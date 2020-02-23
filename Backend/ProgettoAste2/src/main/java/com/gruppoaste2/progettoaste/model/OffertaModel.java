package com.gruppoaste2.progettoaste.model;

import java.util.Date;

public class OffertaModel {

    private final double creditoOfferto;
    private final Date dataOfferta;
    private final UtenteRegistratoModel utenteOfferente;

    public OffertaModel(double creditoOfferto, Date dataOfferta, UtenteRegistratoModel utenteOfferente) {
        this.creditoOfferto = creditoOfferto;
        this.dataOfferta = dataOfferta;
        this.utenteOfferente = utenteOfferente;
    }

    public double getCreditoOfferto() {
        return creditoOfferto;
    }

    public Date getDataOfferta() {
        return dataOfferta;
    }

    public UtenteRegistratoModel getUtenteOfferente() {
        return utenteOfferente;
    }
}