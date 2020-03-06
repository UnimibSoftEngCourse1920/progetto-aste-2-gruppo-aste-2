package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AmministratoreDAO {

    int inserisciAmministratore(UUID id, AmministratoreModel amministratoreModel);

    default int inserisciAmministratore(AmministratoreModel amministratore)
    {
        UUID id = UUID.randomUUID();
        return inserisciAmministratore(id,amministratore);
    }

    int eliminaAmministratore(UUID id);

    Optional<AmministratoreModel> trovaAmministratore(UUID id);

    List<AmministratoreModel> trovaAmministratori();

    int aggiornaAmministratore(UUID id, AmministratoreModel amministratoreAggiornato);

    boolean controllaUsernameOccupato(String username);

    boolean controllaEmailOccupata(String email);

    boolean controllaAmministratoreEsiste(AmministratoreModel amministratore);

    UUID ritornaIdAmministratore(AmministratoreModel amministratore);
}
