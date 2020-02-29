package com.gruppoaste2.progettoaste.model;

import java.util.Map;
import java.util.UUID;

public class CategoriaModel {

    private final UUID id;
    private final Map<String, String> attributi;
    private final String nome;

    public CategoriaModel(UUID id, Map<String, String> attributi, String nome) {
        this.id = id;
        this.attributi = attributi;
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, String> getAttributi() {
        return attributi;
    }

    public String getNome() {
        return nome;
    }
}
