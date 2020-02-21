package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class UtenteRegistratoModel extends UtenteModel {
    private String telefono;
    PortafoglioModel portafoglio;


    public UtenteRegistratoModel(UUID id, String username, String email, String telefono) {
        super(id, username, email);
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }
}
