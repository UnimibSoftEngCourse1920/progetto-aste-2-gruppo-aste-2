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

    List<OggettoModel> trovaOggettiRegistratiDaUtente(UUID idAstaManager);

    List<OggettoModel> trovaOggettiInCorsoAstaUtente(UUID idAstaManager);

    List<OggettoModel> trovaOggettiVendutiDaUtente(UUID idAstaManager);

    List<OggettoModel> trovaOggettiRifiutatiUtente(UUID idAstaManager);

    List<OggettoModel> trovaOggettiVintiDaUtente(UUID idUtente);

    int aggiornaOggetto(UUID id, OggettoModel oggettoAggiornato);

    List<OggettoModel> importaOggetti(String urlFile);

    String esportaOggetti(UUID idAsta, String urlFile);
}
