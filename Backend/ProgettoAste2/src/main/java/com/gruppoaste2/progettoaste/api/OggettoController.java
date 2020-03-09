package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import com.gruppoaste2.progettoaste.service.OggettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/oggetto")
@RestController
public class OggettoController {

    private final OggettoService oggettoService;

    @Autowired
    public OggettoController(OggettoService oggettoService) {
        this.oggettoService = oggettoService;
    }

    @PostMapping("/inserisci/{id}")
    public int inserisciOggetto(@PathVariable("id") UUID idAsta, @RequestBody OggettoModel oggetto)
    {
        return oggettoService.inserisciOggetto(idAsta, oggetto);
    }

    @GetMapping(path = "/elimina/{id}")
    public int eliminaOggetto(@PathVariable UUID id)
    {
        return oggettoService.eliminaOggetto(id);
    }

    @GetMapping(path = "/{id}")
    public OggettoModel trovaOggetto(@PathVariable("id") UUID id)
    {
        return oggettoService.trovaOggetto(id)
                .orElse(null);
    }

    @GetMapping("/oggetti")
    public List<OggettoModel> trovaOggetti()
    {
        return oggettoService.trovaOggetti();
    }

    @GetMapping(path = "/asta/{id}")
    public List<OggettoModel> trovaOggettiAsta(@PathVariable("id") UUID idAsta)
    {
        return oggettoService.trovaOggettiAsta(idAsta);
    }

    @GetMapping(path = "/registrati/{id}")
    public List<OggettoModel> trovaOggettiRegistratiDaUtente(@PathVariable("id") UUID idUtente)
    {
        return oggettoService.trovaOggettiRegistratiDaUtente(idUtente);
    }

    @GetMapping(path = "/incorso/{id}")
    public List<OggettoModel> trovaOggettiInCorsoAstaUtente(@PathVariable("id") UUID idUtente)
    {
        return oggettoService.trovaOggettiInCorsoAstaUtente(idUtente);
    }

    @GetMapping(path = "/vinti/{id}")
    public List<OggettoModel> trovaOggettiVintiDaUtente(@PathVariable("id") UUID idUtente)
    {
        return oggettoService.trovaOggettiVintiDaUtente(idUtente);
    }

    @PostMapping(path = "/aggiorna{id}")
    public int aggiornaOggetto(@PathVariable("id") UUID idOggetto, @RequestBody OggettoModel oggettoAggiornato)
    {
        return oggettoService.aggiornaOggetto(idOggetto, oggettoAggiornato);
    }
}
