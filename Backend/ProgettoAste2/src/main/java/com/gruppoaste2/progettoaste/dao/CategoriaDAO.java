package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaDAO {

    int inserisciCategoria(UUID idCategoria, CategoriaModel categoria);

    default int inserisciCategoria(CategoriaModel categoria)
    {
        UUID id = UUID.randomUUID();
        return inserisciCategoria(id,categoria);
    }

    int eliminaCategoria(UUID id);

    Optional<CategoriaModel> trovaCategoria(UUID id);

    List<CategoriaModel> trovaCategorie();

    int aggiornaCategoria (UUID id, CategoriaModel categoriaAggiornata);
}
