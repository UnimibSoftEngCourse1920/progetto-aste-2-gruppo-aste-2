package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository
public class PostgresCategoriaDAO implements CategoriaDAO{
    @Override
    public boolean insersciCategoria(CategoriaModel categtegoria) {
        return false;
    }

    @Override
    public boolean eliminaCategoria(UUID id) {
        return false;
    }

    @Override
    public CategoriaModel trovaCategoria(UUID id) {
        return null;
    }

    @Override
    public List<CategoriaModel> trovaCategorie() {
        return Collections.emptyList();
    }

    @Override
    public boolean aggiornaCategoria(UUID id, CategoriaModel categoriAggiornata) {
        return false;
    }
}
