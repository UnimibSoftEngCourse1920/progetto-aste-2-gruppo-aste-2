package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.AttributoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaDAO {

    UUID aggiungiCategoria(UUID idCategoria, CategoriaModel categoria);

    default UUID aggiungiCategoria(CategoriaModel categoria)
    {
        UUID id = UUID.randomUUID();
        return aggiungiCategoria(id, categoria);
    }

    int assegnaCategoriaAdOggetto(UUID idOggetto, UUID idCategoria);

    int eliminaCategoria(UUID id);

    Optional<CategoriaModel> trovaCategoria(UUID id);

    List<CategoriaModel> trovaCategorie();

    List<AttributoModel> trovaAttributiCategoria(UUID idCategoria);

    List<CategoriaModel> trovaCategorieOggetto (UUID idOggetto);

    String valoreAttributoOggetto(UUID idOggetto, UUID idAttributo);

    int aggiornaCategoria (UUID id, CategoriaModel categoriaAggiornata);
}
