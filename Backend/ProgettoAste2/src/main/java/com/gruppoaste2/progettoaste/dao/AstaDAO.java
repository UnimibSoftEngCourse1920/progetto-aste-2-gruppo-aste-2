package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AstaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AstaDAO {

    UUID aggiungiAsta(UUID id, AstaModel asta);

    default UUID aggiungiAsta(AstaModel asta)
    {
        UUID id = UUID.randomUUID();
        return aggiungiAsta(id, asta);
    }

    int eliminaAsta(UUID id);

    Optional<AstaModel> trovaAsta(UUID id);

    List<AstaModel> trovaAste();

    List<AstaModel> trovaAsteInCorso();

    List<AstaModel> trovaAsteInCorsoUtente(UUID idAstaManager);

    List<AstaModel> trovaAsteScaduteUtente(UUID idAstaManager);

    List<AstaModel> trovaAsteInCorsoOfferente(UUID idOfferente);

    List<AstaModel> trovaAsteInCorsoBustaChiusaOfferente(UUID idOfferente);

    List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(UUID idOfferente);

    List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(UUID idOfferente);

    List<AstaModel> trovaAsteVinteDaUtente(UUID idUtente);

    int aggiornaAsta(UUID id, AstaModel astaAggiornata);

    Float rinunciaAsta(UUID idAsta, UUID idUtente);
}
