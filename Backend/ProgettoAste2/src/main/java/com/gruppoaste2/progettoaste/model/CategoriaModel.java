package com.gruppoaste2.progettoaste.model;

import java.util.Map;
import java.util.UUID;

public class CategoriaModel {
    private final UUID id;
    private final Map<String, T> attributi,

    public CategoriaModel(UUID id, Map<String, T> attributi) {
        this.id = id;
        this.attributi = attributi;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, T> getAttributi() {
        return attributi;
    }
}
