package com.gruppoaste2.progettoaste.model;

public class InfoCreditoModel {

    private final float creditoTotale;
    private final float creditoDisponibile;
    private final float creditoImpegnato;

    public InfoCreditoModel(float creditoTotale, float creditoDisponibile, float creditoImpegnato) {
        this.creditoTotale = creditoTotale;
        this.creditoDisponibile = creditoDisponibile;
        this.creditoImpegnato = creditoImpegnato;
    }

    public float getCreditoTotale() {
        return creditoTotale;
    }

    public float getCreditoDisponibile() {
        return creditoDisponibile;
    }

    public float getCreditoImpegnato() {
        return creditoImpegnato;
    }
}
