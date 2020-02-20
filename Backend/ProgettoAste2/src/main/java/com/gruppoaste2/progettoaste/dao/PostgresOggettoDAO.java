package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Repository("postgres") // serve per identificare il tipo di database da usare (per dipendency injection)
public class PostgresOggettoDAO implements OggettoDAO {
    @Override
    public int inserisciOggetto(UUID id, OggettoModel oggetto) {
        return 0;
    }

    @Override
    public int eliminaOggetto(UUID id) {
        return 0;
    }

    @Override
    public List<OggettoModel> trovaOggetti() {
        return Collections.emptyList();
    }

    @Override
    public List<OggettoModel> trovaOggetti(UUID idAsta) {
        return Collections.emptyList();
    }

    @Override
    public OggettoModel trovaOggetto(UUID idOggetto) {
        return null;
    }

    @Override
    public int aggiornaOggetto(UUID idOggetto) {
        return 0;
    }
}
