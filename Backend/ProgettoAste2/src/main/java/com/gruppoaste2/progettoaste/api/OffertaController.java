package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.OffertaModel;
import com.gruppoaste2.progettoaste.service.OffertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * La classe {@link RestController @RestController} {@code OffertaController}
 * rappresenta le funzionalità della classe {@code OffertaModel},
 * richiamabili esternamente tramite richieste HTTP.
 * <p>
 * L'URL dell'endpoint è definito tramite l'annotazione
 * {@code {@link RequestMapping @RequestMapping}("api/offerta")}.
 * Le funzioni in questa classe sono accessibili tramite richieste HTTP.
 */
@RequestMapping("api/offerta")
@RestController
public class OffertaController {

    private final OffertaService offertaService;

    @Autowired
    public OffertaController(OffertaService offertaService) {
        this.offertaService = offertaService;
    }

    @PostMapping(path = "/aggiungi/{idAsta}")
    public UUID aggiungiOfferta(@PathVariable("idAsta") UUID idAsta, @RequestBody OffertaModel offerta)
    {
        return offertaService.aggiungiOfferta(idAsta, offerta);
    }

    @GetMapping(path = "/elimina/{idOfferta}")
    public int eliminaOfferta(@PathVariable("idOfferta") UUID idOfferta)
    {
        return offertaService.eliminaOfferta(idOfferta);
    }

    @GetMapping(path = "/{idOfferta}")
    public OffertaModel trovaOfferta(@PathVariable("idOfferta") UUID idOfferta)
    {
        return offertaService.trovaOfferta(idOfferta)
                .orElse(null);
    }

    @GetMapping("/offerte")
    public List<OffertaModel> trovaOfferte()
    {
        return offertaService.trovaOfferte();
    }

    @GetMapping("/offerte/asta/{idAsta}")
    public List<OffertaModel> trovaOfferteAsta(@PathVariable("idAsta") UUID idAsta)
    {
        return offertaService.trovaOfferteAsta(idAsta);
    }

    @GetMapping("/offerte/asta/{idAsta}/ultima")
    public OffertaModel trovaUltimaOffertaAsta(@PathVariable("idAsta") UUID idAsta)
    {
        return offertaService.trovaUltimaOffertaAsta(idAsta)
                .orElse(null);
    }

    @GetMapping("/offerte/asta/{idAsta}/maggiore")
    public OffertaModel trovaOffertaMaggioreAsta(@PathVariable("idAsta") UUID idAsta)
    {
        return offertaService.trovaOffertaMaggioreAsta(idAsta)
                .orElse(null);
    }

    @GetMapping("/offerte/offerente/{idOfferente}")
    public List<OffertaModel> trovaOfferteUtente(@PathVariable("idOfferente") UUID idOfferente)
    {
        return offertaService.trovaOfferteUtente(idOfferente);
    }

    @GetMapping("/offerte/{idOfferente}/{idAsta}")
    public List<OffertaModel> trovaOfferteUtenteAsta(@PathVariable("idOfferente") UUID idOfferente,
                                                     @PathVariable("idAsta") UUID idAsta)
    {
        return offertaService.trovaOfferteUtenteAsta(idOfferente, idAsta);
    }

    @PostMapping(path = "/aggiorna/{idOfferta}")
    public int aggiornaOfferta(@PathVariable("idOfferta") UUID idOfferta, @RequestBody OffertaModel offertaAggiornata)
    {
        return offertaService.aggiornaOfferta(idOfferta, offertaAggiornata);
    }

    @GetMapping("/controlla/offerta/utente/asta/{idUtente}/{idAsta}")
    public boolean controllaOffertaUtenteAstaEsiste(@PathVariable("idUtente") UUID idUtente,
                                                    @PathVariable("idAsta") UUID idAsta)
    {
        return offertaService.controllaOffertaUtenteAstaEsiste(idUtente, idAsta);
    }
}
