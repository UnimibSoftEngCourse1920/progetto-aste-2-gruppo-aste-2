package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AstaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AstaDAO {

    int aggiungiAsta(UUID id, AstaModel asta);

    default int aggiungiAsta(AstaModel asta)
    {
        UUID id = UUID.randomUUID();
        return aggiungiAsta(id, asta);
    }

    int eliminaAsta(UUID id);

    Optional<AstaModel> trovaAsta(UUID id);

    List<AstaModel> trovaAste();

    int aggiornaAsta(UUID id, AstaModel astaAggiornata);
}
