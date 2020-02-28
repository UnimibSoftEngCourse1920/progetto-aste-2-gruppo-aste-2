package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.CategoriaModel;

import java.util.List;
import java.util.UUID;

public interface CategoriaDAO {

    public boolean inserisciCategoria(CategoriaModel categoria);

    public boolean eliminaCategoria(UUID id);

    public CategoriaModel trovaCategoria(UUID id);

    public List<CategoriaModel> trovaCategorie();

    public boolean aggiornaCategoria (UUID id, CategoriaModel categoriaAggiornata);
}
