package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.CategoriaDAO;
import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaService {

    private final CategoriaDAO categoriaDAO;

    @Autowired
    public CategoriaService(@Qualifier("postgres-categoria") CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public UUID aggiungiCategoria(CategoriaModel categoria){
        return categoriaDAO.aggiungiCategoria(categoria);
    }

    public UUID aggiungiAttributoCategoria(UUID idCategoria, AttributoModel attributo){
        return categoriaDAO.aggiungiAttributoCategoria(idCategoria, attributo);
    }

    public int eliminaCategoria(UUID idCategoria){
        return categoriaDAO.eliminaCategoria(idCategoria);
    }

    public int eliminaAttributiCategoria(UUID idCategoria){
        return categoriaDAO.eliminaAttributiCategoria(idCategoria);
    }

    public Optional<CategoriaModel> trovaCategoria(UUID idCategoria){
        return categoriaDAO.trovaCategoria(idCategoria);
    }

    public List<CategoriaModel> trovaCategorie(){
        return categoriaDAO.trovaCategorie();
    }

    public List<AttributoModel> trovaAttributiCategoria(UUID idCategoria){
        return categoriaDAO.trovaAttributiCategoria(idCategoria);
    }

    public List<CategoriaModel> trovaCategorieOggetto (UUID idOggetto){
        return categoriaDAO.trovaCategorieOggetto(idOggetto);
    }

    public String valoreAttributoOggetto(UUID idOggetto, UUID idAttributo){
        return categoriaDAO.valoreAttributoOggetto(idOggetto, idAttributo);
    }

    public int aggiornaCategoria (UUID idCategoria, CategoriaModel categoriaAggiornata){
        return categoriaDAO.aggiornaCategoria(idCategoria, categoriaAggiornata);
    }

    public int assegnaCategoriaAdOggetto(UUID idOggetto, UUID idCategoria) {
        return categoriaDAO.assegnaCategoriaAdOggetto(idOggetto, idCategoria);
    }

    public int rimuoviCategoriaDaOggetto(UUID idOggetto, UUID idCategoria) {
        return categoriaDAO.rimuoviCategoriaDaOggetto(idOggetto, idCategoria);
    }

    public int assegnaValoreAttributoAdOggetto(UUID idOggetto, AttributoModel attributo) {
        return categoriaDAO.assegnaValoreAttributoAdOggetto(idOggetto, attributo);
    }

    public int rimuoviValoreAttributoDaOggetto(UUID idOggetto, UUID idAttributo) {
        return categoriaDAO.rimuoviValoreAttributoDaOggetto(idOggetto, idAttributo);
    }
}
