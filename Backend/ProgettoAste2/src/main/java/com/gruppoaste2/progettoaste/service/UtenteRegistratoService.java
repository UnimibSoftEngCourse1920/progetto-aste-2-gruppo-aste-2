package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.UtenteRegistratoDAO;
import com.gruppoaste2.progettoaste.model.InfoCreditoModel;
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

    public int aggiungiUtenteRegistrato(UtenteRegistratoModel utenteRegistratoModel) {
        return utenteRegistratoDAO.aggiungiUtenteRegistrato(utenteRegistratoModel);
    }

    public int eliminaUtenteRegistrato(UUID id){
        return utenteRegistratoDAO.eliminaUtenteRegistrato(id);
    }

    public Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID id){
        return utenteRegistratoDAO.trovaUtenteRegistrato(id);
    }

    public List<UtenteRegistratoModel> trovaUtentiRegistrati(){
        return utenteRegistratoDAO.trovaUtentiRegistrati();
    }

    public int aggiornaUtenteRegistrato(UUID id, UtenteRegistratoModel utenteAggiornato){
        return utenteRegistratoDAO.aggiornaUtenteRegistrato(id, utenteAggiornato);
    }

    public boolean controllaUsernameOccupato(String username)
    {
        return utenteRegistratoDAO.controllaUsernameOccupato(username);
    }

    public boolean controllaEmailOccupata(String email)
    {
        return utenteRegistratoDAO.controllaEmailOccupata(email);
    }

    public boolean controllaUtenteEsiste(UtenteRegistratoModel utente)
    {
        return utenteRegistratoDAO.controllaUtenteEsiste(utente);
    }

    public int aggiungiCredito(UUID id, float creditoAggiunto)
    {
        return utenteRegistratoDAO.aggiungiCredito(id,creditoAggiunto);
    }

    public InfoCreditoModel infoCredito(UUID id) {
        final float creditoTotale = utenteRegistratoDAO.creditoTotale(id);
        final float creditoImpegnato = utenteRegistratoDAO.creditoImpegnato(id);
        final float creditoDisponibile = creditoTotale - creditoImpegnato;

        return new InfoCreditoModel(creditoTotale, creditoDisponibile, creditoImpegnato);
    }

    public UUID ritornaIdUtenteRegistrato(UtenteRegistratoModel utente)
    {
        return utenteRegistratoDAO.ritornaIdUtenteRegistrato(utente);
    }

    public boolean isNotificheEmailAbilitate(UUID id) {
        return utenteRegistratoDAO.isNotificheEmailAbilitate(id);
    }


    public boolean isNotificheSmsAbilitate(UUID id) {
        return utenteRegistratoDAO.isNotificheSmsAbilitate(id);
    }
}
