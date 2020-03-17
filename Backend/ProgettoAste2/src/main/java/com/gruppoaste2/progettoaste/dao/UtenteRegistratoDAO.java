package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UtenteRegistratoDAO {

    UUID aggiungiUtenteRegistrato(UUID idUtenteRegistrato, UtenteRegistratoModel utenteRegistrato);

    default UUID aggiungiUtenteRegistrato(UtenteRegistratoModel utenteRegistrato)
    {
        UUID idUtenteRegistrato = UUID.randomUUID();
        return aggiungiUtenteRegistrato(idUtenteRegistrato,utenteRegistrato);
    }

    int eliminaUtenteRegistrato(UUID idUtenteRegistrato);

    Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID idUtenteRegistrato);

    List<UtenteRegistratoModel> trovaUtentiRegistrati();

    int aggiornaUtenteRegistrato(UUID idUtenteRegistrato, UtenteRegistratoModel utenteRegistratoAggiornato);

    boolean controllaUsernameOccupato(String username);

    boolean controllaEmailOccupata(String email);

    boolean controllaUtenteEsiste(UtenteRegistratoModel utente);

    UUID ritornaIdUtenteRegistrato(UtenteRegistratoModel utente);

    int aggiungiCredito(UUID idUtenteRegistrato, float creditoAggiunto);

    float creditoTotale(UUID idUtenteRegistrato);

    float creditoImpegnato(UUID idUtenteRegistrato);

    boolean isNotificheEmailAbilitate(UUID idUtenteRegistrato);

    boolean isNotificheSmsAbilitate(UUID idUtenteRegistrato);
}
