package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AmministratoreDAO {

    public int inserisciAmministratore(UUID id, AmministratoreModel amministratoreModel);

    default int inserisciAmministratore(AmministratoreModel amministratore)
    {
        UUID id = UUID.randomUUID();
        return inserisciAmministratore(id,amministratore);
    }

    public boolean eliminaAmministratore(UUID id);

    public Optional<AmministratoreModel> trovaAmministratore(UUID id);

    public Optional<List<AmministratoreModel>> trovaAmministratori();

    public boolean aggiornaAmministratore(UUID id, AmministratoreModel amministratoreAggiornato);
}
