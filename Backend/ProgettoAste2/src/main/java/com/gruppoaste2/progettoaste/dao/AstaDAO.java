package com.gruppoaste2.progettoaste.dao;

import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.model.OffertaModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AstaDAO {

    UUID aggiungiAsta(UUID idAsta, AstaModel asta);

    default UUID aggiungiAsta(AstaModel asta)
    {
        UUID idAsta = UUID.randomUUID();
        return aggiungiAsta(idAsta, asta);
    }

    int eliminaAsta(UUID idAsta);

    Optional<AstaModel> trovaAsta(UUID idAsta);

    List<AstaModel> trovaAste();

    List<AstaModel> trovaAsteInCorso();

    List<AstaModel> trovaAsteInCorsoUtente(UUID idAstaManager);

    List<AstaModel> trovaAsteScaduteUtente(UUID idAstaManager);

    List<AstaModel> trovaAsteInCorsoOfferente(UUID idOfferente);

    List<AstaModel> trovaAsteInCorsoBustaChiusaOfferente(UUID idOfferente);

    List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(UUID idOfferente);

    List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(UUID idOfferente);

    List<AstaModel> trovaAsteVinteDaUtente(UUID idUtente);

    List<AstaModel> trovaAsteAccettateDaUtente(UUID idUtente);

    List<AstaModel> trovaAsteRifiutateDaUtente(UUID idUtente);

    int aggiornaAsta(UUID idAsta, AstaModel astaAggiornata);

    int iniziaAsta(UUID idAsta);

    UUID partecipaAdAsta(UUID idAsta, OffertaModel offerta);

    int chiudiAsta(UUID idAsta);

    Float accettaAstaVinta(UUID idAsta, UUID idVincitore);

    Float rinunciaAstaVinta(UUID idAsta, UUID idVincitore);
}
