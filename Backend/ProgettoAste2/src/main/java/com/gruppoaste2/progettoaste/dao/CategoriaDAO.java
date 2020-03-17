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
        UUID idCategoria = UUID.randomUUID();
        return aggiungiCategoria(idCategoria, categoria);
    }

    int eliminaCategoria(UUID idCategoria);

    Optional<CategoriaModel> trovaCategoria(UUID idCategoria);

    List<CategoriaModel> trovaCategorie();

    List<AttributoModel> trovaAttributiCategoria(UUID idCategoria);

    List<CategoriaModel> trovaCategorieOggetto(UUID idOggetto);

    String valoreAttributoOggetto(UUID idOggetto, UUID idAttributo);

    int aggiornaCategoria (UUID idCategoria, CategoriaModel categoriaAggiornata);

    int assegnaCategoriaAdOggetto(UUID idOggetto, UUID idCategoria);
}
