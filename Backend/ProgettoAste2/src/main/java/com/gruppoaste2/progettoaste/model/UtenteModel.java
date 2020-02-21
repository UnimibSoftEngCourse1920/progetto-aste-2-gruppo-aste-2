package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public abstract class UtenteModel {
    private UUID id;
    private String username;
    private String email;

    public UtenteModel(UUID id, String username, String email){
        this.id = id;
        this.username = username;
        this.email = email;
    }
    public  String getUsername(){
        return username;
    }

    public UUID getId(){
        return id;
    }

    public String getEmail(){
        return email;
    }
}
