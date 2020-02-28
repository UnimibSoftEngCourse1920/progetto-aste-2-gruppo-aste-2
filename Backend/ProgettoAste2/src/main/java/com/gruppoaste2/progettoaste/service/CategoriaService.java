package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.CategoriaDAO;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriaService {
    private final CategoriaDAO categoriaDAO;

    @Autowired
    public CategoriaService(@Qualifier("postgres-categoria") CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public boolean inserisciCategoria(CategoriaModel categoria){
        return categoriaDAO.inserisciCategoria(categoria);

    }

    public boolean eliminaCategoria(UUID id){
        return categoriaDAO.eliminaCategoria(id);
    }

    public CategoriaModel trovaCategoria(UUID id){
        return categoriaDAO.trovaCategoria(id);
    }

    public List<CategoriaModel> trovaCategorie(){
        return categoriaDAO.trovaCategorie();
    }

    public boolean aggiornaCategoria (UUID id, CategoriaModel categoriaAggiornata){
        return categoriaDAO.aggiornaCategoria(id, categoriaAggiornata);
    }
}
