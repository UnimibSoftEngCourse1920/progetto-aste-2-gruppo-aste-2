package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OffertaModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres-offerta")
public class PostgresOffertaDAO implements OffertaDAO{
    @Override
    public int inserisciOfferta(UUID id, OffertaModel offerta) {
        return 0;
    }

    @Override
    public int eliminaOfferta(UUID id) {
        return 0;
    }

    @Override
    public Optional<OffertaModel> trovaOfferta(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<OffertaModel> trovaTutteOfferte() {
        return null;
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteAsta(UUID idAsta) {
        return null;
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteUtente(UUID idUtente) {
        return null;
    }

    @Override
    public List<OffertaModel> trovaTutteOfferteUtenteAsta(UUID idUtente, UUID idAsta) {
        return null;
    }

    @Override
    public int aggiornaOfferta(UUID id, OffertaModel offertaAggiornata) {
        return 0;
    }
}
