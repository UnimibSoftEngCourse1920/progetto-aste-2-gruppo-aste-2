package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.service.AstaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/asta")
@RestController
public class AstaController {

    private final AstaService astaService;

    @Autowired
    public AstaController(AstaService astaService) {
        this.astaService = astaService;
    }

    @PostMapping("/aggiungi")
    public UUID aggiungiAsta(@RequestBody AstaModel asta)
    {
        return astaService.aggiungiAsta(asta);
    }

    @GetMapping(path = "/elimina/{idAsta}")
    public int eliminaAsta(@PathVariable("idAsta") UUID idAsta)
    {
        return astaService.eliminaAsta(idAsta);
    }

    @GetMapping(path = "/{idAsta}")
    public AstaModel trovaAsta(@PathVariable("idAsta") UUID idAsta)
    {
        return astaService.trovaAsta(idAsta)
                .orElse(null);
    }

    @GetMapping("/aste")
    public List<AstaModel> trovaAste()
    {
        return astaService.trovaAste();
    }

    @GetMapping("/aste/incorso")
    public List<AstaModel> trovaAsteInCorso()
    {
        return astaService.trovaAsteInCorso();
    }

    @GetMapping("/aste/incorso/{idAstaManager}")
    public List<AstaModel> trovaAsteInCorsoUtente(@PathVariable("idAstaManager") UUID idAstaManager)
    {
        return astaService.trovaAsteInCorsoUtente(idAstaManager);
    }

    @GetMapping("/aste/scadute/{idAstaManager}")
    public List<AstaModel> trovaAsteScaduteUtente(@PathVariable("idAstaManager") UUID idAstaManager)
    {
        return astaService.trovaAsteScaduteUtente(idAstaManager);
    }

    @GetMapping("/aste/incorso/offerente/{idOfferente}")
    public List<AstaModel> trovaAsteInCorsoOfferente(@PathVariable("idOfferente") UUID idOfferente)
    {
        return astaService.trovaAsteInCorsoOfferente(idOfferente);
    }

    @GetMapping("/aste/incorso/busta-chiusa/offerente/{idOfferente}")
    public List<AstaModel> trovaAsteInCorsoBustaChiusaOfferente(@PathVariable("idOfferente") UUID idOfferente)
    {
        return astaService.trovaAsteInCorsoBustaChiusaOfferente(idOfferente);
    }

    @GetMapping("/aste/incorso/superamento-immediato/massimo-offerente/{idOfferente}")
    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(@PathVariable("idOfferente")
                                                                                             UUID idOfferente)
    {
        return astaService.trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(idOfferente);
    }

    @GetMapping("/aste/incorso/superamento-immediato/offerente-superato/{idOfferente}")
    public List<AstaModel> trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(@PathVariable("idOfferente")
                                                                                             UUID idOfferente)
    {
        return astaService.trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(idOfferente);
    }

    @GetMapping("/aste/vinte/{idUtente}")
    public List<AstaModel> trovaAsteVinteDaUtente(@PathVariable("idUtente") UUID idUtente)
    {
        return astaService.trovaAsteVinteDaUtente(idUtente);
    }

    @PostMapping(path = "/aggiorna/{idAsta}")
    public int aggiornaAsta(@PathVariable("idAsta") UUID idAsta, @RequestBody AstaModel astaAggiornata)
    {
        return astaService.aggiornaAsta(idAsta, astaAggiornata);
    }

    @GetMapping(path = "/accetta/{idAsta}/{idVincitore}")
    public Float accettaAstaVinta(@PathVariable("idAsta") UUID idAsta, @PathVariable("idVincitore") UUID idVincitore)
    {
        return astaService.accettaAstaVinta(idAsta, idVincitore);
    }

    @GetMapping(path = "/rinuncia/{idAsta}/{idVincitore}")
    public Float rinunciaAstaVinta(@PathVariable("idAsta") UUID idAsta, @PathVariable("idVincitore") UUID idVincitore)
    {
        return astaService.rinunciaAstaVinta(idAsta, idVincitore);
    }
}
