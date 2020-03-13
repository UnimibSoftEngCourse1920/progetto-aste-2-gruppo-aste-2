package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.OggettoDAO;
import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OggettoService {

    private final OggettoDAO oggettoDAO;

    @Autowired
    public OggettoService(@Qualifier("postgres-oggetto") OggettoDAO oggettoDAO) {
        this.oggettoDAO = oggettoDAO;
    }

    public int inserisciOggetto(UUID idAsta, OggettoModel oggetto)
    {
        return oggettoDAO.inserisciOggetto(idAsta,oggetto);
    }

    public int eliminaOggetto(UUID id)
    {
        return oggettoDAO.eliminaOggetto(id);
    }

    public Optional<OggettoModel> trovaOggetto(UUID id)
    {
        return oggettoDAO.trovaOggetto(id);
    }

    public List<OggettoModel> trovaOggetti()
    {
        return oggettoDAO.trovaOggetti();
    }

    public List<OggettoModel> trovaOggettiAsta(UUID idAsta)
    {
        return oggettoDAO.trovaOggettiAsta(idAsta);
    }

    public List<OggettoModel> trovaOggettiRegistratiDaUtente(UUID idUtente)
    {
        return oggettoDAO.trovaOggettiRegistratiDaUtente(idUtente);
    }

    public List<OggettoModel> trovaOggettiInCorsoAstaUtente(UUID idUtente)
    {
        return oggettoDAO.trovaOggettiInCorsoAstaUtente(idUtente);
    }

    public List<OggettoModel> trovaOggettiVendutiDaUtente(UUID idUtente)
    {
        return oggettoDAO.trovaOggettiVendutiDaUtente(idUtente);
    }

    public List<OggettoModel> trovaOggettiRifiutatiUtente(UUID idUtente)
    {
        return oggettoDAO.trovaOggettiRifiutatiUtente(idUtente);
    }

    public List<OggettoModel> trovaOggettiVintiDaUtente(UUID idUtente)
    {
        return oggettoDAO.trovaOggettiVintiDaUtente(idUtente);
    }

    public int aggiornaOggetto(UUID id, OggettoModel oggettoAggiornato)
    {
        return oggettoDAO.aggiornaOggetto(id, oggettoAggiornato);
    }
}
