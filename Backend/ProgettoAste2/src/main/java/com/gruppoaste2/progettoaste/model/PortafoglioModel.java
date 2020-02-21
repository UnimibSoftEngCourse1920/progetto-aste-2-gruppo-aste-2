package com.gruppoaste2.progettoaste.model;

public class PortafoglioModel {

    private final double creditoDisponibile;

    public PortafoglioModel(double creditoDisponibile){
        this.creditoDisponibile = creditoDisponibile;
    }

    public double getCreditoDisponibile() {
        return this.creditoDisponibile;
    }
}
