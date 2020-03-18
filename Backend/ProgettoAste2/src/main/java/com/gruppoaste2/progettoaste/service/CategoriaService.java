package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.CategoriaDAO;
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

    public String aggiungiCategoria(CategoriaModel categoria){
        return categoriaDAO.aggiungiCategoria(categoria);
    }

    public int eliminaCategoria(String idCategoria){
        return categoriaDAO.eliminaCategoria(idCategoria);
    }

    public Optional<CategoriaModel> trovaCategoria(String idCategoria) {
        return categoriaDAO.trovaCategoria(idCategoria);
    }

    public List<CategoriaModel> trovaCategorie(){
        return categoriaDAO.trovaCategorie();
    }

    public List<CategoriaModel> trovaCategorieOggetto (UUID idOggetto) {
        return categoriaDAO.trovaCategorieOggetto(idOggetto);
    }

    public int aggiornaCategoria(String idCategoria, CategoriaModel categoriaAggiornata) {
        return categoriaDAO.aggiornaCategoria(idCategoria, categoriaAggiornata);
    }

    public boolean controllaCategoriaEsiste(CategoriaModel categoria) {
        return categoriaDAO.controllaCategoriaEsiste(categoria);
    }

    public int assegnaCategoriaAdOggetto(UUID idOggetto, String idCategoria) {
        return categoriaDAO.assegnaCategoriaAdOggetto(idOggetto, idCategoria);
    }

    public int rimuoviCategoriaDaOggetto(UUID idOggetto, String idCategoria) {
        return categoriaDAO.rimuoviCategoriaDaOggetto(idOggetto, idCategoria);
    }
}
