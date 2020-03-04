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

    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/asta<h1>";
    }

    @GetMapping("/aste")
    public List<AstaModel> trovaAste()
    {
        return astaService.trovaTutteAste();
    }

    @GetMapping(path="/{id}")
    public AstaModel trovaAsta(@PathVariable("id") UUID id)
    {
        return astaService.trovaAsta(id)
                .orElse(null);
    }

    @PostMapping("/aggiungi")
    public int aggiungiAsta(@RequestBody AstaModel asta)
    {
        return astaService.inserisciAsta(asta);
    }

    @PostMapping(path = "/aggiorna/{id}")
    public int aggiornaAsta(@PathVariable("id") UUID id, @RequestBody AstaModel astaAggiornata)
    {
        return astaService.aggiornaAsta(id, astaAggiornata);
    }

    @GetMapping(path = "elimina/{id}") // funziona
    public int eliminaAsta(@PathVariable("id") UUID id)
    {
        return astaService.eliminaAsta(id);
    }

}
