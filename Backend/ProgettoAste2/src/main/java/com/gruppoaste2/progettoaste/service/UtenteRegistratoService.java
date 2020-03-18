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

    public UUID aggiungiUtenteRegistrato(UtenteRegistratoModel utenteRegistratoModel) {
        return utenteRegistratoDAO.aggiungiUtenteRegistrato(utenteRegistratoModel);
    }

    public int eliminaUtenteRegistrato(UUID idUtenteRegistrato) {
        return utenteRegistratoDAO.eliminaUtenteRegistrato(idUtenteRegistrato);
    }

    public Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID idUtenteRegistrato) {
        return utenteRegistratoDAO.trovaUtenteRegistrato(idUtenteRegistrato);
    }

    public List<UtenteRegistratoModel> trovaUtentiRegistrati(){
        return utenteRegistratoDAO.trovaUtentiRegistrati();
    }

    public int aggiornaUtenteRegistrato(UUID idUtenteRegistrato, UtenteRegistratoModel utenteRegistratoAggiornato) {
        return utenteRegistratoDAO.aggiornaUtenteRegistrato(idUtenteRegistrato, utenteRegistratoAggiornato);
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

    public UUID ritornaIdUtenteRegistrato(UtenteRegistratoModel utente)
    {
        return utenteRegistratoDAO.ritornaIdUtenteRegistrato(utente);
    }

    public int aggiungiCredito(UUID idUtenteRegistrato, float creditoAggiunto)
    {
        return utenteRegistratoDAO.aggiungiCredito(idUtenteRegistrato, creditoAggiunto);
    }

    public InfoCreditoModel infoCredito(UUID idUtenteRegistrato) {
        final float creditoTotale = utenteRegistratoDAO.creditoTotale(idUtenteRegistrato);
        final float creditoImpegnato = utenteRegistratoDAO.creditoImpegnato(idUtenteRegistrato);
        final float creditoDisponibile = creditoTotale - creditoImpegnato;

        return new InfoCreditoModel(creditoTotale, creditoDisponibile, creditoImpegnato);
    }

    public boolean isNotificheEmailAbilitate(UUID idUtenteRegistrato) {
        return utenteRegistratoDAO.isNotificheEmailAbilitate(idUtenteRegistrato);
    }

    public boolean isNotificheSmsAbilitate(UUID idUtenteRegistrato) {
        return utenteRegistratoDAO.isNotificheSmsAbilitate(idUtenteRegistrato);
    }
}
