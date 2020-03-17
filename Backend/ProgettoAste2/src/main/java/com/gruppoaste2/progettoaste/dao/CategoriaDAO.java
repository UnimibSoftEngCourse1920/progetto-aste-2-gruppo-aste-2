package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.AttributoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaDAO {

    String aggiungiCategoria(CategoriaModel categoria);

    UUID aggiungiAttributoCategoria(UUID idAttributo, String idCategoria, AttributoModel attributo);

    default UUID aggiungiAttributoCategoria(String idCategoria, AttributoModel attributo)
    {
        UUID idAttributo = UUID.randomUUID();
        return aggiungiAttributoCategoria(idAttributo, idCategoria, attributo);
    }

    int eliminaCategoria(String idCategoria);

    int eliminaAttributiCategoria(String idCategoria);

    Optional<CategoriaModel> trovaCategoria(String idCategoria);

    List<CategoriaModel> trovaCategorie();

    List<AttributoModel> trovaAttributiCategoria(String idCategoria);

    List<CategoriaModel> trovaCategorieOggetto(UUID idOggetto);

    String valoreAttributoOggetto(UUID idOggetto, UUID idAttributo);

    int aggiornaCategoria(String idCategoria, CategoriaModel categoriaAggiornata);

    boolean controllaCategoriaEsiste(CategoriaModel categoria);

    int assegnaCategoriaAdOggetto(UUID idOggetto, String idCategoria);

    int rimuoviCategoriaDaOggetto(UUID idOggetto, String idCategoria);

    int assegnaValoreAttributoAdOggetto(UUID idOggetto, AttributoModel attributo);

    int rimuoviValoreAttributoDaOggetto(UUID idOggetto, UUID idAttributo);
}
