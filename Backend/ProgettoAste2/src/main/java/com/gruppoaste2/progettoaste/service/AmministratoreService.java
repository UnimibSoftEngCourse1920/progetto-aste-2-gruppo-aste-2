package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.AmministratoreDAO;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AmministratoreService {
    private final AmministratoreDAO amministratoreDAO;

    @Autowired
    public AmministratoreService(@Qualifier("postgres-amministratore") AmministratoreDAO amministratoreDAO) {
        this.amministratoreDAO = amministratoreDAO;
    }

    public int inserisciAmministratore(AmministratoreModel amministratoreModel){
        return amministratoreDAO.inserisciAmministratore(amministratoreModel);
    }

    public int eliminaAmministratore(UUID id){
        return amministratoreDAO.eliminaAmministratore(id);
    }

    public Optional<AmministratoreModel> trovaAmministratore(UUID id){
        return amministratoreDAO.trovaAmministratore(id);
    }

    public Optional<List<AmministratoreModel>> trovaAmministratori(){
        return amministratoreDAO.trovaAmministratori();
    }

    public int aggiornaAmministratore(UUID id, AmministratoreModel amministratoreAggiornato){
        return amministratoreDAO.aggiornaAmministratore(id, amministratoreAggiornato);
    }

    public boolean controllaUsernameOccupato(String username)
    {
        return amministratoreDAO.controllaUsernameOccupato(username);
    }

    public boolean controllaEmailOccupata(String email)
    {
        return amministratoreDAO.controllaEmailOccupata(email);
    }

    public boolean controllaAmministratoreEsiste(AmministratoreModel amministratore)
    {
        return amministratoreDAO.controllaAmministratoreEsiste(amministratore);
    }
}
