package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AstaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AstaDAO {

    int inserisciAsta(UUID id, AstaModel asta);

    default int inserisciAsta(AstaModel asta)
    {
        UUID id = UUID.randomUUID();
        return inserisciAsta(id, asta);
    }

    int eliminaAsta(UUID id);

    Optional<AstaModel> trovaAsta(UUID id);

    List<AstaModel> trovaTutteAste();

    int aggiornaAsta(UUID id, AstaModel astaAggiornata);
}
