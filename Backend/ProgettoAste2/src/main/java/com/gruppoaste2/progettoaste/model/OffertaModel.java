package com.gruppoaste2.progettoaste.model;

import java.util.UUID;
import java.util.Date;

public class OffertaModel {

    private final UUID id;
    private final float creditoOfferto;
    private final Date dataOfferta;
    private final UtenteRegistratoModel utenteOfferente;

    public OffertaModel(UUID id, float creditoOfferto, Date dataOfferta, UtenteRegistratoModel utenteOfferente) {
        this.id = id;
        this.creditoOfferto = creditoOfferto;
        this.dataOfferta = dataOfferta;
        this.utenteOfferente = utenteOfferente;
    }

    public UUID getId() { return id; }

    public float getCreditoOfferto() {
        return creditoOfferto;
    }

    public Date getDataOfferta() {
        return dataOfferta;
    }

    public UtenteRegistratoModel getUtenteOfferente() {
        return utenteOfferente;
    }
}
