package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.AttributoDAO;
import com.gruppoaste2.progettoaste.model.AttributoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttributoService {

    private final AttributoDAO attributoDAO;

    @Autowired
    public AttributoService(@Qualifier("postgres-attributo") AttributoDAO attributoDAO) {
        this.attributoDAO = attributoDAO;
    }

    public UUID aggiungiAttributo(String idCategoria, AttributoModel attributo) {
        return attributoDAO.aggiungiAttributo(idCategoria, attributo);
    }

    public int eliminaAttributo(UUID idAttributo) {
        return attributoDAO.eliminaAttributo(idAttributo);
    }

    public Optional<AttributoModel> trovaAttributo(UUID idAttributo) {
        return attributoDAO.trovaAttributo(idAttributo);
    }

    public List<AttributoModel> trovaAttributi(){
        return attributoDAO.trovaAttributi();
    }

    public List<AttributoModel> trovaAttributiCategoria(String idCategoria) {
        return attributoDAO.trovaAttributiCategoria(idCategoria);
    }

    public List<AttributoModel> trovaAttributiOggetto(UUID idOggetto) {
        return attributoDAO.trovaAttributiOggetto(idOggetto);
    }

    public int aggiornaAttributo(UUID idAttributo, AttributoModel attributoAggiornato) {
        return attributoDAO.aggiornaAttributo(idAttributo, attributoAggiornato);
    }

    public int aggiornaAttributoOggetto(UUID idAttributo, UUID idOggetto, AttributoModel attributoAggiornato) {
        return attributoDAO.aggiornaAttributoOggetto(idAttributo, idOggetto, attributoAggiornato);
    }

    public int assegnaValoreAttributoAdOggetto(UUID idOggetto, AttributoModel attributo) {
        return attributoDAO.assegnaValoreAttributoAdOggetto(idOggetto, attributo);
    }

    public int rimuoviValoreAttributoDaOggetto(UUID idOggetto, UUID idAttributo) {
        return attributoDAO.rimuoviValoreAttributoDaOggetto(idOggetto, idAttributo);
    }
}
