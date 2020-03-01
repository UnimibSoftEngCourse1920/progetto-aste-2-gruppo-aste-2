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

    public int inserisciOfferta(UUID idAsta, OffertaModel offerta) {
        return offertaDAO.inserisciOfferta(idAsta, offerta);
    }

    public int eliminaOfferta(UUID id){
        return offertaDAO.eliminaOfferta(id);
    }

    public Optional<OffertaModel> trovaOfferta(UUID id){
        return offertaDAO.trovaOfferta(id);
    }

    public List<OffertaModel> trovaTutteOfferte(){
        return offertaDAO.trovaTutteOfferte();
    }

    public List<OffertaModel> trovaTutteOfferteAsta(UUID idAsta){
        return offertaDAO.trovaTutteOfferteAsta(idAsta);
    }

    public List<OffertaModel> trovaTutteOfferteUtente(UUID idUtenteOfferente){
        return offertaDAO.trovaTutteOfferteUtente(idUtenteOfferente);
    }

    public List<OffertaModel> trovaTutteOfferteUtenteAsta(UUID idUtenteOfferente, UUID idAsta){
        return offertaDAO.trovaTutteOfferteUtenteAsta(idUtenteOfferente, idAsta);
    }

    public int aggiornaOfferta(UUID id, UUID idAsta, OffertaModel offertaAggiornata){
        return offertaDAO.aggiornaOfferta(id, idAsta, offertaAggiornata);
    }
}
