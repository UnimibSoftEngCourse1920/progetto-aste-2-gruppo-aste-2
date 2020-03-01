package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.OffertaModel;
import com.gruppoaste2.progettoaste.service.OffertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/offerta")
@RestController
public class OffertaController {

    private final OffertaService offertaService;

    @Autowired
    public OffertaController(OffertaService offertaService) {
        this.offertaService = offertaService;
    }

    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/offerta<h1>";
    }

    @GetMapping("/offerte")
    public List<OffertaModel> trovaTutteOfferte()
    {
        return offertaService.trovaTutteOfferte();
    }

    @GetMapping(path="{id}")
    public OffertaModel trovaOfferta(@PathVariable("id") UUID id)
    {
        return offertaService.trovaOfferta(id)
                .orElse(null);
    }

    @GetMapping("/offerte/{idAsta}")
    public List<OffertaModel> trovaTutteOfferteAsta(@PathVariable("idAsta") UUID idAsta)
    {
        return offertaService.trovaTutteOfferteAsta(idAsta);
    }

    @GetMapping("/offerte/{idUtenteOfferente}")
    public List<OffertaModel> trovaTutteOfferteUtente(@PathVariable("idUtenteOfferente") UUID idUtenteOfferente)
    {
        return offertaService.trovaTutteOfferteUtente(idUtenteOfferente);
    }

    @GetMapping("/offerte/{idUtenteOfferente}/{idAsta}")
    public List<OffertaModel> trovaTutteOfferteUtenteAsta(@PathVariable("idUtenteOfferente") UUID idUtenteOfferente, @PathVariable("idAsta") UUID idAsta)
    {
        return offertaService.trovaTutteOfferteUtenteAsta(idUtenteOfferente, idAsta);
    }

    @PostMapping("/aggiungi")
    public int aggiungiOfferta(UUID idAsta, @RequestBody OffertaModel offerta)
    {
        return offertaService.inserisciOfferta(idAsta, offerta);
    }

    @PostMapping(path = "/aggiorna/{id}")
    public int aggiornaOfferta(@PathVariable("id") UUID id, UUID idAsta, @RequestBody OffertaModel utenteAggiornato)
    {
        return offertaService.aggiornaOfferta(id, idAsta, utenteAggiornato);
    }

    @GetMapping(path = "elimina/{id}")
    public int eliminaOfferta(@PathVariable("id") UUID id)
    {
        return offertaService.eliminaOfferta(id);
    }
}
