package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class UtenteRegistratoModel extends UtenteModel {

    private final String numeroTelefono;
    private final float credito;
    private boolean notificheEmail;
    private boolean notificheSms;

    public UtenteRegistratoModel(UUID id, String username, String email, String numeroTelefono, String password,
                                 float credito, boolean notificheEmail, boolean notificheSms){
        super(id, username, email, password);
        this.numeroTelefono = numeroTelefono;
        this.credito = credito;
        this.notificheEmail = notificheEmail;
        this.notificheSms = notificheSms;
    }



    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public float getCredito()
    {
        return credito;
    }

    public boolean isNotificheEmail() {
        return notificheEmail;
    }

    public boolean isNotificheSms() {
        return notificheSms;
    }
}
