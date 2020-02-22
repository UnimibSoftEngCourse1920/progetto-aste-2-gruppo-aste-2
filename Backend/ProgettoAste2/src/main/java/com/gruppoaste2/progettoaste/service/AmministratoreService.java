package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.AmministratoreDAO;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AmministratoreService {
    private final AmministratoreDAO amministratoreDAO;

    @Autowired
    public AmministratoreService(@Qualifier("postgres-amministratore") AmministratoreDAO amministratoreDAO) {
        this.amministratoreDAO = amministratoreDAO;
    }

    public boolean insersciAmministratore(AmministratoreModel amministratoreModel){
        return amministratoreDAO.insersciamministratore(amministratoreModel);
    }

    public boolean eliminaAmministratore(UUID id){
        return amministratoreDAO.eliminaAmministratore(id);
    }

    public AmministratoreModel trovaAmministratore(UUID id){
        return amministratoreDAO.trovaAmministratore(id);
    }

    public List<AmministratoreModel> trovaAmministratori(){
        return amministratoreDAO.trovaAmministratori();
    }

    public boolean aggiornaAmministratore(UUID id, AmministratoreModel amministratoreAggiornato){
        return amministratoreDAO.aggiornaAmministratore(id, amministratoreAggiornato);
    }
}
