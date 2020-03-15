package com.gruppoaste2.progettoaste.model;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.Date;

public class OffertaModel {

    private final UUID id;
    private final float creditoOfferto;
    private final Timestamp dataOfferta;
    private final UtenteRegistratoModel offerente;

    public OffertaModel(UUID id, float creditoOfferto, Timestamp dataOfferta, UtenteRegistratoModel offerente) {
        this.id = id;
        this.creditoOfferto = creditoOfferto;
        this.dataOfferta = dataOfferta;
        this.offerente = offerente;
    }

    public UUID getId() {
        return id;
    }

    public float getCreditoOfferto() {
        return creditoOfferto;
    }

    public Timestamp getDataOfferta() {
        return dataOfferta;
    }

    public UtenteRegistratoModel getOfferente() {
        return offerente;
    }
}
