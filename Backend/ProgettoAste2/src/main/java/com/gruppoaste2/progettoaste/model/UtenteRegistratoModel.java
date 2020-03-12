package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class UtenteRegistratoModel extends UtenteModel {

    private final String numeroTelefono;
    private final float credito;
    private boolean notificheSms;
    private boolean notificheEmail;

    public UtenteRegistratoModel(UUID id, String username, String email, String numeroTelefono, String password,
                                 float credito, boolean notificheSms, boolean notificheEmail){
        super(id, username, email, password);
        this.numeroTelefono = numeroTelefono;
        this.credito = credito;
        this.notificheEmail = notificheEmail;
        this.notificheSms = notificheEmail;
    }



    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public float getCredito()
    {
        return credito;
    }

    public boolean isNotificheSms() {
        return notificheSms;
    }

    public boolean isNotificheEmail() {
        return notificheEmail;
    }
}
