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

    public int assegnaCategoriaAdOggetto(UUID idOggetto, UUID idCategoria) {
        return categoriaDAO.assegnaCategoriaAdOggetto(idOggetto, idCategoria);
    }

    public int eliminaCategoria(UUID id){
        return categoriaDAO.eliminaCategoria(id);
    }

    public Optional<CategoriaModel> trovaCategoria(UUID id){
        return categoriaDAO.trovaCategoria(id);
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

    public int aggiornaCategoria (UUID id, CategoriaModel categoriaAggiornata){
        return categoriaDAO.aggiornaCategoria(id, categoriaAggiornata);
    }
}
