package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AttributoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttributoDAO {

    UUID aggiungiAttributo(UUID idAttributo, String idCategoria, AttributoModel attributo);

    default UUID aggiungiAttributo(String idCategoria, AttributoModel attributo)
    {
        UUID idAttributo = UUID.randomUUID();
        return aggiungiAttributo(idAttributo, idCategoria, attributo);
    }

    int eliminaAttributo(UUID idAttributo);

    Optional<AttributoModel> trovaAttributo(UUID idAttributo);

    List<AttributoModel> trovaAttributi();

    List<AttributoModel> trovaAttributiCategoria(String idCategoria);

    List<AttributoModel> trovaAttributiOggetto(UUID idOggetto);

    int aggiornaAttributo(UUID idAttributo, AttributoModel attributoAggiornato);

    int aggiornaAttributoOggetto(UUID idAttributo, UUID idOggetto, AttributoModel attributoAggiornato);

    int assegnaValoreAttributoAdOggetto(UUID idOggetto, AttributoModel attributo);

    int rimuoviValoreAttributoDaOggetto(UUID idOggetto, UUID idAttributo);
}
