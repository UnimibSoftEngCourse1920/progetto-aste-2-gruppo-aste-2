package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.AstaDAO;
import com.gruppoaste2.progettoaste.model.AstaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AstaService {

    private final AstaDAO astaDAO;

    @Autowired
    public AstaService(@Qualifier("postgres-asta") AstaDAO astaDAO) {
        this.astaDAO = astaDAO;
    }

    public UUID aggiungiAsta(AstaModel astaModel) {
        return astaDAO.aggiungiAsta(astaModel);
    }

    public int eliminaAsta(UUID idAsta){
        return astaDAO.eliminaAsta(idAsta);
    }

    public Optional<AstaModel> trovaAsta(UUID idAsta){
        return astaDAO.trovaAsta(idAsta);
    }

    public List<AstaModel> trovaAste(){
        return astaDAO.trovaAste();
    }

    public List<AstaModel> trovaAsteInCorso(){
        return astaDAO.trovaAsteInCorso();
    }

    public List<AstaModel> trovaAsteInCorsoUtente(UUID idAstaManager){
        return astaDAO.trovaAsteInCorsoUtente(idAstaManager);
    }

    public List<AstaModel> trovaAsteScaduteUtente(UUID idAstaManager){
        return astaDAO.trovaAsteScaduteUtente(idAstaManager);
    }

    public List<AstaModel> trovaAsteInCorsoOfferente(UUID idOfferente){
        return astaDAO.trovaAsteInCorsoOfferente(idOfferente);
    }

    public List<AstaModel> trovaAsteInCorsoBustaChiusaOfferente(UUID idOfferente){
        return astaDAO.trovaAsteInCorsoBustaChiusaOfferente(idOfferente);
    }

    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(UUID idOfferente){
        return astaDAO.trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(idOfferente);
    }

    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(UUID idOfferente){
        return astaDAO.trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(idOfferente);
    }

    public List<AstaModel> trovaAsteVinteDaUtente(UUID idUtente){
        return astaDAO.trovaAsteVinteDaUtente(idUtente);
    }

    public int aggiornaAsta(UUID idAsta, AstaModel astaAggiornata){
        return astaDAO.aggiornaAsta(idAsta, astaAggiornata);
    }

    public Float accettaAstaVinta(UUID idAsta, UUID idVincitore){
        return astaDAO.accettaAstaVinta(idAsta, idVincitore);
    }

    public Float rinunciaAstaVinta(UUID idAsta, UUID idVincitore){
        return astaDAO.rinunciaAstaVinta(idAsta, idVincitore);
    }
}
