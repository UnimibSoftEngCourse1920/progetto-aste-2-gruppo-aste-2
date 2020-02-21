package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class UtenteRegistratoModel extends UtenteModel {

    private final String numeroTelefono;
    private final PortafoglioModel portafoglio;


    public UtenteRegistratoModel(UUID id, String username, String email, String numeroTelefono, PortafoglioModel portafoglio) {
        super(id, username, email);
        this.numeroTelefono = numeroTelefono;
        this.portafoglio = portafoglio;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getUsername()
    {
        return super.getUsername();
    }

    public String getEmail()
    {
        return super.getEmail();
    }

    public PortafoglioModel getPortafoglio()
    {
        return portafoglio;
    }
}
