package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.CategoriaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaDAO {

    String aggiungiCategoria(CategoriaModel categoria);

    int eliminaCategoria(String idCategoria);

    Optional<CategoriaModel> trovaCategoria(String idCategoria);

    List<CategoriaModel> trovaCategorie();

    List<CategoriaModel> trovaCategorieOggetto(UUID idOggetto);

    int aggiornaCategoria(String idCategoria, CategoriaModel categoriaAggiornata);

    boolean controllaCategoriaEsiste(CategoriaModel categoria);

    int assegnaCategoriaAdOggetto(UUID idOggetto, String idCategoria);

    int rimuoviCategoriaDaOggetto(UUID idOggetto, String idCategoria);
}
