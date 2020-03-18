package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.OffertaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OffertaDAO {

    UUID aggiungiOfferta(UUID idOfferta, UUID idAsta, OffertaModel offerta);

    default UUID aggiungiOfferta(UUID idAsta, OffertaModel offerta)
    {
        UUID idOfferta = UUID.randomUUID();
        return aggiungiOfferta(idOfferta, idAsta, offerta);
    }

    int eliminaOfferta(UUID idOfferta);

    Optional<OffertaModel> trovaOfferta(UUID idOfferta);

    List<OffertaModel> trovaOfferte();

    List<OffertaModel> trovaOfferteAsta(UUID idAsta);

    Optional<OffertaModel> trovaUltimaOffertaAsta(UUID idAsta);

    Optional<OffertaModel> trovaOffertaMaggioreAsta(UUID idAsta);

    List<OffertaModel> trovaOfferteUtente(UUID idOfferente);

    List<OffertaModel> trovaOfferteUtenteAsta(UUID idOfferente, UUID idAsta);

    int aggiornaOfferta(UUID idOfferta, OffertaModel offertaAggiornata);

    boolean controllaOffertaUtenteAstaEsiste(UUID idUtente, UUID idAsta);
}
