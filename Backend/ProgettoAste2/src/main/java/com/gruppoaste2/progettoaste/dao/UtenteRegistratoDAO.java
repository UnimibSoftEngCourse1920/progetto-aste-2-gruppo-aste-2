package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UtenteRegistratoDAO {

    int inserisciUtenteRegistrato(UUID id, UtenteRegistratoModel utenteRegistrato);

    default int inserisciUtenteRegistrato(UtenteRegistratoModel utenteRegistrato)
    {
        UUID id = UUID.randomUUID();
        return inserisciUtenteRegistrato(id,utenteRegistrato);
    }

    int eliminaUtenteRegistrato(UUID id);

    Optional<UtenteRegistratoModel> trovaUtenteRegistrato(UUID id);

    List<UtenteRegistratoModel> trovaTuttiUtentiRegistrati();

    boolean aggiornaUtenteRegistrato(UUID id, UtenteRegistratoModel utenteAggiornato);

    boolean controllaUsernameOccupato(String username);

    boolean controllaEmailOccupata(String email);

    boolean controllaUtenteEsiste(UtenteRegistratoModel utente);

}
