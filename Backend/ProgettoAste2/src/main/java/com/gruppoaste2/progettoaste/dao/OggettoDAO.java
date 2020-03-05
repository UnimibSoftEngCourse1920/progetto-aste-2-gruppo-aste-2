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

    List<OggettoModel> trovaOggetti();

    List<OggettoModel> trovaOggetti(UUID idAsta);

    Optional<OggettoModel> trovaOggetto(UUID idOggetto);

    int aggiornaOggetto(UUID idOggetto, OggettoModel oggettoAggiornato);

    List<OggettoModel> oggettiRegistratiDaUtente(UUID idUtente);

    List<OggettoModel> oggettiInCorsoAstaDaUtente(UUID idUtente);

    List<OggettoModel> oggettiVintiDaUtente(UUID idUtente);

}
