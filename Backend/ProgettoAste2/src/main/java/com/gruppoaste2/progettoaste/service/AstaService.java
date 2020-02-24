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
    private final AstaDAO AstaDAO;

    @Autowired
    public AstaService(@Qualifier("postgres-asta") AstaDAO astaDAO) {
        AstaDAO = astaDAO;
    }

    public boolean inserisciAsta(AstaModel asta){
        return AstaDAO.inserisciAsta(asta);
    }

    public boolean eliminaAsta (UUID id){
        return  AstaDAO.eliminaAsta(id);
    }

    public AstaModel trovaAsta(UUID id){
        return AstaDAO.trovaAsta(id);
    }

    public List<AstaModel> trovaAste(){
        return AstaDAO.trovaAste();
    }

    public boolean aggiornaAsta(UUID id, AstaModel astAggiornata){
        return AstaDAO.aggiornaAsta(id, astAggiornata);
    }

    public boolean faiOffertaBustaChiusa(UUID id, double offerta){
        return AstaDAO.faiOffertaBustaChiusa(id, offerta);
    }

    public boolean faiOffertaSuperamentoImmediato(UUID id, double offerta){
        return AstaDAO.faiOffertaSuperamentoImmediato(id, offerta);
    }
}
