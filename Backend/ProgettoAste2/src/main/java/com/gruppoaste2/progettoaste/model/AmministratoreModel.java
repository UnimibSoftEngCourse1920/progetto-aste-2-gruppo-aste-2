package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class AmministratoreModel extends UtenteModel {

    public AmministratoreModel(UUID id, String username, String email) {
        super(id, username, email);
    }

    @Override
    public UUID getId() {
        return super.getId();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }
}
