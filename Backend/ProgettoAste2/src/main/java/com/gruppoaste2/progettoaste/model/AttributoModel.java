package com.gruppoaste2.progettoaste.model;

import java.util.UUID;

public class AttributoModel {

    private final UUID id;
    private final String nome;

    public AttributoModel(UUID id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
