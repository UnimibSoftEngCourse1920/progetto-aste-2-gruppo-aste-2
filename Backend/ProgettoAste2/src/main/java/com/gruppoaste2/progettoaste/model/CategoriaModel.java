package com.gruppoaste2.progettoaste.model;

import java.util.Map;
import java.util.UUID;

public class CategoriaModel {

    private final UUID id;
    private final String nome;
    private final Map<String, String> attributi;
    // private final List<AttributoModel> attributi;

    public CategoriaModel(UUID id, String nome, Map<String, String> attributi) {
        this.id = id;
        this.nome = nome;
        this.attributi = attributi;
    }

    /*
    public CategoriaModel(UUID id, String nome, List<AttributoModel> attributi) {
        this.id = id;
        this.nome = nome;
        this.attributi = attributi;
    }
     */

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Map<String, String> getAttributi() {
        return attributi;
    }

    /*
    public List<AttributoModel> getAttributi() {
        return attributi;
    }
     */
}
