package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OggettoDAO {

    int inserisciOggetto(UUID idOggetto, UUID idAsta, OggettoModel oggetto);

    default int inserisciOggetto(UUID idAsta, OggettoModel oggetto)
    {
        UUID id = UUID.randomUUID();
        return inserisciOggetto(id, idAsta, oggetto);
    }

    int eliminaOggetto(UUID id);

    Optional<OggettoModel> trovaOggetto(UUID id);

    List<OggettoModel> trovaOggetti();

    List<OggettoModel> trovaOggettiAsta(UUID idAsta);

    int aggiornaOggetto(UUID idOggetto, OggettoModel oggettoAggiornato);

    List<OggettoModel> trovaOggettiRegistratiDaUtente(UUID idUtente);

    List<OggettoModel> trovaOggettiInCorsoAstaUtente(UUID idUtente);

    List<OggettoModel> trovaOggettiVintiDaUtente(UUID idUtente);
}
