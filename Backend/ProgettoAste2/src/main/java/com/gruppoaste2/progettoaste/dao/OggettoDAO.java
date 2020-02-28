package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OggettoDAO {

    public int inserisciOggetto(UUID idOggetto, UUID idAsta, OggettoModel oggetto);

    default int inserisciOggetto(UUID idAsta, OggettoModel oggetto)
    {
        UUID id = UUID.randomUUID();
        return inserisciOggetto(id, idAsta, oggetto);
    }

    public int eliminaOggetto(UUID id);

    public List<OggettoModel> trovaOggetti();

    public List<OggettoModel> trovaOggetti(UUID idAsta);

    public Optional<OggettoModel> trovaOggetto(UUID idOggetto);

    public int aggiornaOggetto(UUID idOggetto, OggettoModel oggettoAggiornato);

    public List<OggettoModel> oggettiRegistratiDaUtente(UUID idUtente);

    public List<OggettoModel> oggettiInCorsoAstaDaUtente(UUID idUtente);

    public Optional<List<OggettoModel>> oggettiVintiDaUtente(UUID idUtente);

}
