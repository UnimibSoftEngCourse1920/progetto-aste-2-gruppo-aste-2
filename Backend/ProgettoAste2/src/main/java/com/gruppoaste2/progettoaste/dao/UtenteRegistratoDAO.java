package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UtenteRegistratoDAO {

    int aggiungiUtenteRegistrato(UUID id, UtenteRegistratoModel utenteRegistrato);

    default int aggiungiUtenteRegistrato(UtenteRegistratoModel utenteRegistrato)
    {
        UUID id = UUID.randomUUID();
        return aggiungiUtenteRegistrato(id,utenteRegistrato);
    }

    int eliminaUtenteRegistrato(UUID id);

    Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID id);

    List<UtenteRegistratoModel> trovaUtentiRegistrati();

    int aggiornaUtenteRegistrato(UUID id, UtenteRegistratoModel utenteAggiornato);

    boolean controllaUsernameOccupato(String username);

    boolean controllaEmailOccupata(String email);

    boolean controllaUtenteEsiste(UtenteRegistratoModel utente);

    int aggiungiCredito(UUID id, float creditoAggiunto);

    float creditoTotale(UUID id);

    float creditoImpegnato(UUID id);

    UUID ritornaIdUtenteRegistrato(UtenteRegistratoModel utente);

    boolean isNotificheEmailAbilitate(UUID id);

    boolean isNotificheSmsAbilitate(UUID id);
}
