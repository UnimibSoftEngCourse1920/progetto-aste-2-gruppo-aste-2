package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import com.gruppoaste2.progettoaste.service.OggettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.beans.ExceptionListener;
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
    public UUID inserisciOggetto(@PathVariable("id") UUID idAsta, @RequestBody OggettoModel oggetto)
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

    @GetMapping(path = "/asta/{idAsta}")
    public List<OggettoModel> trovaOggettiAsta(@PathVariable("idAsta") UUID idAsta)
    {
        return oggettoService.trovaOggettiAsta(idAsta);
    }

    @GetMapping(path = "/registrati/{idAstaManager}")
    public List<OggettoModel> trovaOggettiRegistratiDaUtente(@PathVariable("idAstaManager") UUID idAstaManager)
    {
        return oggettoService.trovaOggettiRegistratiDaUtente(idAstaManager);
    }

    @GetMapping(path = "/incorso/{idAstaManager}")
    public List<OggettoModel> trovaOggettiInCorsoAstaUtente(@PathVariable("idAstaManager") UUID idAstaManager)
    {
        return oggettoService.trovaOggettiInCorsoAstaUtente(idAstaManager);
    }

    @GetMapping(path = "/venduti/{idAstaManager}")
    public List<OggettoModel> trovaOggettiVendutiDaUtente(@PathVariable("idAstaManager") UUID idAstaManager)
    {
        return oggettoService.trovaOggettiVendutiDaUtente(idAstaManager);
    }

    @GetMapping(path = "/rifiutati/{idAstaManager}")
    public List<OggettoModel> trovaOggettiRifiutatiUtente(@PathVariable("idAstaManager") UUID idAstaManager)
    {
        return oggettoService.trovaOggettiRifiutatiUtente(idAstaManager);
    }

    @GetMapping(path = "/vinti/{idUtente}")
    public List<OggettoModel> trovaOggettiVintiDaUtente(@PathVariable("idUtente") UUID idUtente)
    {
        return oggettoService.trovaOggettiVintiDaUtente(idUtente);
    }

    @PostMapping(path = "/aggiorna{id}")
    public int aggiornaOggetto(@PathVariable("id") UUID id, @RequestBody OggettoModel oggettoAggiornato)
    {
        return oggettoService.aggiornaOggetto(id, oggettoAggiornato);
    }

    @PostMapping(path = "/importa/{idAsta}")
    public long importaOggetti(@PathVariable("idAsta") UUID idAsta, @RequestBody String fileName)
    {
        return oggettoService.importaOggetti(idAsta, fileName);
    }

    @PostMapping(path = "/esporta/{idAsta}")
    public long esportaOggetti(@PathVariable("idAsta") UUID idAsta, @RequestBody String fileName)
    {
        return oggettoService.esportaOggetti(idAsta, fileName);
    }
}
