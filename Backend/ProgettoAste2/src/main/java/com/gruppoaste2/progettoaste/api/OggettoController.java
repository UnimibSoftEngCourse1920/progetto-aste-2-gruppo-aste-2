package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import com.gruppoaste2.progettoaste.service.OggettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @PostMapping(path = "/aggiorna{id}")
    public int aggiornaOggetto(@PathVariable("id") UUID idOggetto, @RequestBody OggettoModel oggettoAggiornato)
    {
        return oggettoService.aggiornaOggetto(idOggetto, oggettoAggiornato);
    }

    @GetMapping("/oggetti")
    public List<OggettoModel> trovaOggetti()
    {
        return oggettoService.trovaOggetti();
    }

    @GetMapping(path = "/{id}")
    public Optional<OggettoModel> trovaOggetto(@PathVariable("id") UUID idOggetto)
    {
        return oggettoService.trovaOggetto(idOggetto);
    }

    @GetMapping(path = "/asta/{id}")
    public List<OggettoModel> trovaOggettiAsta(@PathVariable("id") UUID idAsta)
    {
        return oggettoService.trovaOggetti(idAsta);
    }

    @GetMapping(path = "/registrati/{id}")
    public List<OggettoModel> oggettiRegistratiDaUtente(@PathVariable("id") UUID idUtente)
    {
        return oggettoService.oggettiRegistratiDaUtente(idUtente);
    }

    @GetMapping(path = "incorso/{id}")
    public List<OggettoModel> oggettiInCorsoAstaUtente(@PathVariable("id") UUID idUtente)
    {
        return oggettoService.oggettiInCorsoAstaDaUtente(idUtente);
    }

    @GetMapping(path = "/vinti/{id}")
    public List<OggettoModel> oggettiVintiDaUtente(@PathVariable("id") UUID idUtente)
    {
        return oggettoService.oggettiVintiDaUtente(idUtente);
    }
}
