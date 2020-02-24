package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class UtenteRegistratoModel extends UtenteModel {

    private final String numeroTelefono;
    private final double credito;

    public UtenteRegistratoModel(UUID id, String username, String email, String numeroTelefono, String password, double credito){
        super(id, username, email,password);
        this.numeroTelefono = numeroTelefono;
        this.credito = credito;

    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public double getCredito()
    {
        return credito;
    }


}
