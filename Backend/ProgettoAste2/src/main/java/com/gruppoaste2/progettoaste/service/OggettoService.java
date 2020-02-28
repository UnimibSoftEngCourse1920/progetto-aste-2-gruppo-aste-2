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

    public Optional<OggettoModel> trovaOggetto(UUID idOggetto)
    {
        return oggettoDAO.trovaOggetto(idOggetto);
    }

    public List<OggettoModel> trovaOggetti(UUID idAsta)
    {
        return oggettoDAO.trovaOggetti(idAsta);
    }

    public List<OggettoModel> trovaOggetti()
    {
        return oggettoDAO.trovaOggetti();
    }

    public List<OggettoModel> oggettiRegistratiDaUtente(UUID idUtente)
    {
        return oggettoDAO.oggettiRegistratiDaUtente(idUtente);
    }

    public List<OggettoModel> oggettiInCorsoAstaDaUtente(UUID idUtente)
    {
        return oggettoDAO.oggettiInCorsoAstaDaUtente(idUtente);
    }

    public List<OggettoModel> oggettiVintiDaUtente(UUID idUtente)
    {
        return oggettoDAO.oggettiVintiDaUtente(idUtente);
    }

    public int aggiornaOggetto(UUID idOggetto)
    {
        return oggettoDAO.aggiornaOggetto(idOggetto);
    }


}
