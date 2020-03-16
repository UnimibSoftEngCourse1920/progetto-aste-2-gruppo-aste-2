package com.gruppoaste2.progettoaste.model;

import java.util.List;
import java.util.UUID;

public class OggettoModel {

    private final UUID id;
    private final String nome;
    private final String descrizione;
    private final String urlImmagine;
    private final List<CategoriaModel> categorie;

    public OggettoModel(UUID id, String nome, String descrizione, String urlImmagine, List<CategoriaModel> categorie) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.urlImmagine = urlImmagine;
        this.categorie = categorie;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public List<CategoriaModel> getCategorie() {
        return categorie;
    }
}

