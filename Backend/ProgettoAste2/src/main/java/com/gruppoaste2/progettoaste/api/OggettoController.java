package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import com.gruppoaste2.progettoaste.service.OggettoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.beans.ExceptionListener;
import java.util.List;
import java.util.UUID;

/**
 * La classe {@link RestController @RestController} {@code OggettoController}
 * rappresenta le funzionalità della classe {@code OggettoModel},
 * richiamabili esternamente tramite richieste HTTP.
 * <p>
 * L'URL dell'endpoint è definito tramite l'annotazione
 * {@code {@link RequestMapping @RequestMapping}("api/oggetto")}.
 * Le funzioni in questa classe sono accessibili tramite richieste HTTP.
 */
@RequestMapping("api/oggetto")
@RestController
public class OggettoController {

    private final OggettoService oggettoService;

    @Autowired
    public OggettoController(OggettoService oggettoService) {
        this.oggettoService = oggettoService;
    }


    @PostMapping("/inserisci/{idAsta}")
    public UUID inserisciOggetto(@PathVariable("idAsta") UUID idAsta, @RequestBody OggettoModel oggetto)

    {
        return oggettoService.inserisciOggetto(idAsta, oggetto);
    }

    @GetMapping(path = "/elimina/{idOggetto}")
    public int eliminaOggetto(@PathVariable("idOggetto") UUID idOggetto)
    {
        return oggettoService.eliminaOggetto(idOggetto);
    }

    @GetMapping(path = "/{idOggetto}")
    public OggettoModel trovaOggetto(@PathVariable("idOggetto") UUID idOggetto)
    {
        return oggettoService.trovaOggetto(idOggetto)
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

    @PostMapping(path = "/aggiorna{idOggetto}")
    public int aggiornaOggetto(@PathVariable("idOggetto") UUID idOggetto, @RequestBody OggettoModel oggettoAggiornato)
    {
        return oggettoService.aggiornaOggetto(idOggetto, oggettoAggiornato);
    }

    @PostMapping("/importa")
    public List<OggettoModel> importaOggetti(@RequestBody String oggettiCSV)
    {
        return oggettoService.importaOggetti(oggettiCSV);
    }

    @GetMapping(path = "/esporta/{idAsta}")
    public String esportaOggetti(@PathVariable("idAsta") UUID idAsta)
    {
        return oggettoService.esportaOggetti(idAsta);
    }
}
