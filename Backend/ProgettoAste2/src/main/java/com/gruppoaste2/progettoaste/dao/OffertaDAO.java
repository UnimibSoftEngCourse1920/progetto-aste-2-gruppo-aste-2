package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OffertaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OffertaDAO {

    int inserisciOfferta(UUID id, OffertaModel offerta);

    default int inserisciOfferta(OffertaModel offerta)
    {
        UUID id = UUID.randomUUID();
        return inserisciOfferta(id, offerta);
    }

    int eliminaOfferta(UUID id);

    Optional<OffertaModel> trovaOfferta(UUID id);

    List<OffertaModel> trovaTutteOfferte();

    List<OffertaModel> trovaTutteOfferteAsta(UUID idAsta);

    List<OffertaModel> trovaTutteOfferteUtente(UUID idUtente);

    List<OffertaModel> trovaTutteOfferteUtenteAsta(UUID idUtente, UUID idAsta);

    int aggiornaOfferta(UUID id, OffertaModel offertaAggiornata);
}
