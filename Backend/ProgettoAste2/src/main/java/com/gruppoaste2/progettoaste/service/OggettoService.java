package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.OggettoDAO;
import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OggettoService {

    private final OggettoDAO oggettoDAO;

    @Autowired
    public OggettoService(@Qualifier("postgres") OggettoDAO oggettoDAO) {
        this.oggettoDAO = oggettoDAO;
    }

    public int inserisciOggetto(UUID idOggetto, OggettoModel oggetto)
    {
        return oggettoDAO.inserisciOggetto(idOggetto,oggetto);
    }

    public OggettoModel trovaOggetto(UUID idOggetto)
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

    public int aggiornaOggetto(UUID idOggetto)
    {
        return oggettoDAO.aggiornaOggetto(idOggetto);
    }


}
