package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.AstaDAO;
import com.gruppoaste2.progettoaste.model.AstaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AstaService {
    private final AstaDAO astaDAO;

    @Autowired
    public AstaService(@Qualifier("postgres-asta") AstaDAO astaDAO) {
        this.astaDAO = astaDAO;
    }

    public boolean inserisciAsta(AstaModel asta){
        return astaDAO.inserisciAsta(asta);
    }

    public boolean eliminaAsta (UUID id){
        return  astaDAO.eliminaAsta(id);
    }

    public AstaModel trovaAsta(UUID id){
        return astaDAO.trovaAsta(id);
    }

    public List<AstaModel> trovaAste(){
        return astaDAO.trovaAste();
    }

    public boolean aggiornaAsta(UUID id, AstaModel astAggiornata){
        return astaDAO.aggiornaAsta(id, astAggiornata);
    }

    public boolean faiOffertaBustaChiusa(UUID id, double offerta){
        return astaDAO.faiOffertaBustaChiusa(id, offerta);
    }

    public boolean faiOffertaSuperamentoImmediato(UUID id, double offerta){
        return astaDAO.faiOffertaSuperamentoImmediato(id, offerta);
    }
}
