package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.OggettoDAO;
import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import java.util.*;

import static com.gruppoaste2.progettoaste.service.OggettoCSVHelper.readOggettiCSV;
import static com.gruppoaste2.progettoaste.service.OggettoCSVHelper.writeOggettiCSV;

@Service
public class OggettoService {

    private final OggettoDAO oggettoDAO;

    @Autowired
    public OggettoService(@Qualifier("postgres-oggetto") OggettoDAO oggettoDAO) {
        this.oggettoDAO = oggettoDAO;
    }

    public UUID inserisciOggetto(UUID idAsta, OggettoModel oggetto)
    {
        return oggettoDAO.inserisciOggetto(idAsta,oggetto);
    }

    public int eliminaOggetto(UUID idOggetto)
    {
        return oggettoDAO.eliminaOggetto(idOggetto);
    }

    public Optional<OggettoModel> trovaOggetto(UUID idOggetto)
    {
        return oggettoDAO.trovaOggetto(idOggetto);
    }

    public List<OggettoModel> trovaOggetti()
    {
        return oggettoDAO.trovaOggetti();
    }

    public List<OggettoModel> trovaOggettiAsta(UUID idAsta)
    {
        return oggettoDAO.trovaOggettiAsta(idAsta);
    }

    public List<OggettoModel> trovaOggettiRegistratiDaUtente(UUID idAstaManager)
    {
        return oggettoDAO.trovaOggettiRegistratiDaUtente(idAstaManager);
    }

    public List<OggettoModel> trovaOggettiInCorsoAstaUtente(UUID idAstaManager)
    {
        return oggettoDAO.trovaOggettiInCorsoAstaUtente(idAstaManager);
    }

    public List<OggettoModel> trovaOggettiVendutiDaUtente(UUID idAstaManager)
    {
        return oggettoDAO.trovaOggettiVendutiDaUtente(idAstaManager);
    }

    public List<OggettoModel> trovaOggettiRifiutatiUtente(UUID idAstaManager)
    {
        return oggettoDAO.trovaOggettiRifiutatiUtente(idAstaManager);
    }

    public List<OggettoModel> trovaOggettiVintiDaUtente(UUID idUtente)
    {
        return oggettoDAO.trovaOggettiVintiDaUtente(idUtente);
    }

    public int aggiornaOggetto(UUID idOggetto, OggettoModel oggettoAggiornato)
    {
        return oggettoDAO.aggiornaOggetto(idOggetto, oggettoAggiornato);
    }

    public List<OggettoModel> importaOggetti(String oggettiCSV)
    {
        return readOggettiCSV(oggettiCSV);
    }

    public String esportaOggetti(UUID idAsta)
    {
        return writeOggettiCSV(oggettoDAO.trovaOggettiAsta(idAsta));
    }
}
