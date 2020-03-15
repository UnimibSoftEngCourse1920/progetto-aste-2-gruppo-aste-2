package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OffertaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OffertaDAO {

    int aggiungiOfferta(UUID id, UUID idAsta, OffertaModel offerta);

    default int aggiungiOfferta(UUID idAsta, OffertaModel offerta)
    {
        UUID id = UUID.randomUUID();
        return aggiungiOfferta(id, idAsta, offerta);
    }

    int eliminaOfferta(UUID id);

    Optional<OffertaModel> trovaOfferta(UUID id);

    List<OffertaModel> trovaOfferte();

    List<OffertaModel> trovaOfferteAsta(UUID idAsta);

    Optional<OffertaModel> trovaUltimaOffertaAsta(UUID idAsta);

    Optional<OffertaModel> trovaOffertaMaggioreAsta(UUID idAsta);

    List<OffertaModel> trovaOfferteUtente(UUID idOfferente);

    List<OffertaModel> trovaOfferteUtenteAsta(UUID idOfferente, UUID idAsta);

    int aggiornaOfferta(UUID id, OffertaModel offertaAggiornata);

    boolean controllaOffertaUtenteAstaEsiste(UUID idUtente, UUID idAsta);
}
