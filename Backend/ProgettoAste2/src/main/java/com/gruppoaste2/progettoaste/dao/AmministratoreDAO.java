package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AmministratoreDAO {

    UUID inserisciAmministratore(UUID idAmministratore, AmministratoreModel amministratore);

    default UUID inserisciAmministratore(AmministratoreModel amministratore)
    {
        UUID idAmministratore = UUID.randomUUID();
        return inserisciAmministratore(idAmministratore, amministratore);
    }

    int eliminaAmministratore(UUID idAmministratore);

    Optional<AmministratoreModel> trovaAmministratore(UUID idAmministratore);

    List<AmministratoreModel> trovaAmministratori();

    int aggiornaAmministratore(UUID idAmministratore, AmministratoreModel amministratoreAggiornato);

    boolean controllaUsernameOccupato(String username);

    boolean controllaEmailOccupata(String email);

    boolean controllaAmministratoreEsiste(AmministratoreModel amministratore);

    UUID ritornaIdAmministratore(AmministratoreModel amministratore);
}
