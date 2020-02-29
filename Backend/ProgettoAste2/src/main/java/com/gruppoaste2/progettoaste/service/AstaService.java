package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.AstaDAO;
import com.gruppoaste2.progettoaste.model.AstaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AstaService {

    private final AstaDAO astaDAO;

    @Autowired
    public AstaService(@Qualifier("postgres-asta") AstaDAO astaDAO) {
        this.astaDAO = astaDAO;
    }

    public int inserisciAsta(AstaModel astaModel) {
        return astaDAO.inserisciAsta(astaModel);
    }

    public int eliminaAsta(UUID id){
        return astaDAO.eliminaAsta(id);
    }

    public Optional<AstaModel> trovaAsta(UUID id){
        return astaDAO.trovaAsta(id);
    }

    public List<AstaModel> trovaTutteAste(){
        return astaDAO.trovaTutteAste();
    }

    public int aggiornaAsta(UUID id, AstaModel astaAggiornata){
        return astaDAO.aggiornaAsta(id, astaAggiornata);
    }
}
