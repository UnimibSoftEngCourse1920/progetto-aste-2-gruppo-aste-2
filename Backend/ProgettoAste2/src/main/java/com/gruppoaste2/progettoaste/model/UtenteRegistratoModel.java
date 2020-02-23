package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class UtenteRegistratoModel extends UtenteModel {

    private final String numeroTelefono;
    private final PortafoglioModel portafoglio;


    public UtenteRegistratoModel(UUID id, String username, String email, String numeroTelefono, String password, PortafoglioModel portafoglio) {
        super(id, username, email,password);
        this.numeroTelefono = numeroTelefono;
        this.portafoglio = portafoglio;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public PortafoglioModel getPortafoglio() {
        return portafoglio;
    }
}