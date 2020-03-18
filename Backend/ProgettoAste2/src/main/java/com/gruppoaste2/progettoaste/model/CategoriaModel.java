package com.gruppoaste2.progettoaste.model;

import java.util.List;

public class CategoriaModel {

    private final String id;
    private final List<AttributoModel> attributi;

    public CategoriaModel(String id, List<AttributoModel> attributi) {
        this.id = id;
        this.attributi = attributi;
    }

    public String getId() {
        return id;
    }

    public List<AttributoModel> getAttributi() {
        return attributi;
    }
}
