package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.OffertaDAO;
import com.gruppoaste2.progettoaste.model.OffertaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OffertaService {

    private final OffertaDAO offertaDAO;

    @Autowired
    public OffertaService(@Qualifier("postgres-offerta") OffertaDAO offertaDAO) {
        this.offertaDAO = offertaDAO;
    }

    public UUID aggiungiOfferta(UUID idAsta, OffertaModel offerta) {
        return offertaDAO.aggiungiOfferta(idAsta, offerta);
    }

    public int eliminaOfferta(UUID idOfferta){
        return offertaDAO.eliminaOfferta(idOfferta);
    }

    public Optional<OffertaModel> trovaOfferta(UUID idOfferta){
        return offertaDAO.trovaOfferta(idOfferta);
    }

    public List<OffertaModel> trovaOfferte(){
        return offertaDAO.trovaOfferte();
    }

    public List<OffertaModel> trovaOfferteAsta(UUID idAsta){
        return offertaDAO.trovaOfferteAsta(idAsta);
    }

    public Optional<OffertaModel> trovaUltimaOffertaAsta(UUID idAsta) {
        return offertaDAO.trovaUltimaOffertaAsta(idAsta);
    }

    public Optional<OffertaModel> trovaOffertaMaggioreAsta(UUID idAsta) {
        return offertaDAO.trovaOffertaMaggioreAsta(idAsta);
    }

    public List<OffertaModel> trovaOfferteUtente(UUID idOfferente) {
        return offertaDAO.trovaOfferteUtente(idOfferente);
    }

    public List<OffertaModel> trovaOfferteUtenteAsta(UUID idOfferente, UUID idAsta) {
        return offertaDAO.trovaOfferteUtenteAsta(idOfferente, idAsta);
    }

    public int aggiornaOfferta(UUID idOfferta, OffertaModel offertaAggiornata) {
        return offertaDAO.aggiornaOfferta(idOfferta, offertaAggiornata);
    }

    public boolean controllaOffertaUtenteAstaEsiste(UUID idUtente, UUID idAsta) {
        return offertaDAO.controllaOffertaUtenteAstaEsiste(idUtente, idAsta);
    }
}
