package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class AttributoModel {

    private final UUID id;
    private final String nome;
    private final String valore;

    public AttributoModel(UUID id, String nome, String valore) {
        this.id = id;
        this.nome = nome;
        this.valore = valore;
    }

    public AttributoModel(UUID id, String nome) {
        this(id, nome, null);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getValore() {
        return valore;
    }
}
