package com.gruppoaste2.progettoaste.model;

import java.util.List;
import java.util.UUID;

public class CategoriaModel {

    private final UUID id;
    private final String nome;
    private final List<AttributoModel> attributi;

    public CategoriaModel(UUID id, String nome, List<AttributoModel> attributi) {
        this.id = id;
        this.nome = nome;
        this.attributi = attributi;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<AttributoModel> getAttributi() {
        return attributi;
    }
}
