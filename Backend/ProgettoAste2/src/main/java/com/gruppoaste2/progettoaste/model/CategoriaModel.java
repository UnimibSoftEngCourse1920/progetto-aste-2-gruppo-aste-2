package com.gruppoaste2.progettoaste.model;

import java.util.Map;
import java.util.UUID;

public class CategoriaModel {
    private final UUID id;
    private final Map<String, String> attributi;

    public CategoriaModel(UUID id, Map<String, String> attributi) {
        this.id = id;
        this.attributi = attributi;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, String> getAttributi() {
        return attributi;
    }
}
