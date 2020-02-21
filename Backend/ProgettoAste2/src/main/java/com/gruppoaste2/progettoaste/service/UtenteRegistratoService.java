package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.UtenteRegistratoDAO;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtenteRegistratoService {

    private final UtenteRegistratoDAO utenteRegistratoDAO;

    @Autowired
    public UtenteRegistratoService(@Qualifier("postgres-utente") UtenteRegistratoDAO utenteRegistratoDAO) {
        this.utenteRegistratoDAO = utenteRegistratoDAO;
    }

    public boolean insersciUtenteRegistrato(UtenteRegistratoModel utenteRegistratoModel) {
        return utenteRegistratoDAO.insersciUtenteRegistrato(utenteRegistratoModel);
    }

    public boolean eliminaUtenteRegistrato(UUID id){
        return utenteRegistratoDAO.eliminaUtenteRegistrato(id);
    }

    public UtenteRegistratoModel trovaUtenteRegistro(UUID id){
        return utenteRegistratoDAO.trovaUtenteRegistro(id);
    }

    public List<UtenteRegistratoModel> trovaTuttiUtentiRegistrati(){
        return utenteRegistratoDAO.trovaTuttiUtentiRegistrati();
    }

    public boolean aggiornaUtenteRegistrto(UUID id, UtenteRegistratoModel utenteAggiornato){
        return utenteRegistratoDAO.aggiornaUtenteRegistrto(id, utenteAggiornato);
    }
}
