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
    public int aggiungiAsta(@RequestBody AstaModel asta)
    {
        return astaService.aggiungiAsta(asta);
    }

    @GetMapping(path = "/elimina/{id}")
    public int eliminaAsta(@PathVariable("id") UUID id)
    {
        return astaService.eliminaAsta(id);
    }

    @GetMapping(path = "/{id}")
    public AstaModel trovaAsta(@PathVariable("id") UUID id)
    {
        return astaService.trovaAsta(id)
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

    @GetMapping("/aste/incorso/{idUtente}")
    public List<AstaModel> trovaAsteInCorsoUtente(@PathVariable("idUtente") UUID idUtente)
    {
        return astaService.trovaAsteInCorsoUtente(idUtente);
    }

    @GetMapping("/aste/scadute/{idUtente}")
    public List<AstaModel> trovaAsteScaduteUtente(@PathVariable("idUtente") UUID idUtente)
    {
        return astaService.trovaAsteScaduteUtente(idUtente);
    }

    @PostMapping(path = "/aggiorna/{id}")
    public int aggiornaAsta(@PathVariable("id") UUID id, @RequestBody AstaModel astaAggiornata)
    {
        return astaService.aggiornaAsta(id, astaAggiornata);
    }
}
