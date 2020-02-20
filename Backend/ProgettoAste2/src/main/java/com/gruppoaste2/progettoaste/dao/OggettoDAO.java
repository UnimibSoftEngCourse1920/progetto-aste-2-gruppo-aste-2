package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;

import java.util.List;
import java.util.UUID;

public interface OggettoDAO {

    public int inserisciOggetto(UUID id, OggettoModel oggetto);

    public int eliminaOggetto(UUID id);

    public List<OggettoModel> trovaOggetti();

    public List<OggettoModel> trovaOggetti(UUID idAsta);

    public OggettoModel trovaOggetto(UUID idOggetto);

    public int aggiornaOggetto(UUID idOggetto);

}
