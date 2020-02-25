package com.gruppoaste2.progettoaste.api;


import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.UtenteRegistratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/utenteregistrato")
@RestController
public class UtenteRegistratoController {

    private final UtenteRegistratoService utenteRegistratoService;

    @Autowired
    public UtenteRegistratoController(UtenteRegistratoService utenteRegistratoService) {
        this.utenteRegistratoService = utenteRegistratoService;
    }

    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/utenteregistrato<h1>";
    }

    @GetMapping("/utenti")
    public List<UtenteRegistratoModel> trovaUtentiRegistrati()
    {
        return utenteRegistratoService.trovaTuttiUtentiRegistrati();
    }

    @GetMapping(path="{id}")
    public UtenteRegistratoModel trovaUtenteRegistrato(@PathVariable("id") UUID id)
    {
        return utenteRegistratoService.trovaUtenteRegistro(id)
                .orElse(null);
    }

    @PostMapping("/aggiungi")
    public int aggiungiUtenteRegistrato(@RequestBody UtenteRegistratoModel utente)
    {
        return utenteRegistratoService.inserisciUtenteRegistrato(utente);
    }

    @GetMapping(path = "elimina/{id}") // funziona
    public int eliminaUtenteRegistrato(@PathVariable("id") UUID id)
    {
        return utenteRegistratoService.eliminaUtenteRegistrato(id);
    }

    @GetMapping(path = "controlla/username/{user}")
    public boolean controllaUsernameOccupato(@PathVariable("user") String username)
    {
        return utenteRegistratoService.controllaUsernameOccupato(username);
    }

    @GetMapping(path = "controlla/email/{mail}")
    public boolean controllaEmailOccupata(@PathVariable("mail") String email)
    {
        return utenteRegistratoService.controllaEmailOccupata(email);
    }

    @GetMapping("/controlla/utente")
    public boolean controllaUtenteEsiste(@RequestBody UtenteRegistratoModel utente) // nel body della richiesta posso anche avere non tutti gli attributi dell oggetto
    {
        return utenteRegistratoService.controllaUtenteEsiste(utente);
    }
}
