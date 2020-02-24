package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.UtenteRegistratoDAO;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteRegistratoService {

    private final UtenteRegistratoDAO utenteRegistratoDAO;

    @Autowired
    public UtenteRegistratoService(@Qualifier("postgres-utenteRegistrato") UtenteRegistratoDAO utenteRegistratoDAO) {
        this.utenteRegistratoDAO = utenteRegistratoDAO;
    }

    public int insersciUtenteRegistrato(UtenteRegistratoModel utenteRegistratoModel) {
        return utenteRegistratoDAO.inserisciUtenteRegistrato(utenteRegistratoModel);
    }

    public boolean eliminaUtenteRegistrato(UUID id){
        return utenteRegistratoDAO.eliminaUtenteRegistrato(id);
    }

    public Optional<UtenteRegistratoModel> trovaUtenteRegistro(UUID id){
        return utenteRegistratoDAO.trovaUtenteRegistrato(id);
    }

    public List<UtenteRegistratoModel> trovaTuttiUtentiRegistrati(){
        return utenteRegistratoDAO.trovaTuttiUtentiRegistrati();
    }

    public boolean aggiornaUtenteRegistrto(UUID id, UtenteRegistratoModel utenteAggiornato){
        return utenteRegistratoDAO.aggiornaUtenteRegistrato(id, utenteAggiornato);
    }
}
