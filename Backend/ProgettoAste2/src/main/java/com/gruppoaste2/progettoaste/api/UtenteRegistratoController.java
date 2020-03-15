package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.InfoCreditoModel;
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

    // TODO: Da eliminare una volta finita la fase di testing
    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/utenteregistrato<h1>";
    }

    @PostMapping("/aggiungi")
    public int aggiungiUtenteRegistrato(@RequestBody UtenteRegistratoModel utente)
    {
        return utenteRegistratoService.aggiungiUtenteRegistrato(utente);
    }

    @GetMapping(path = "/elimina/{id}")
    public int eliminaUtenteRegistrato(@PathVariable("id") UUID id)
    {
        return utenteRegistratoService.eliminaUtenteRegistrato(id);
    }

    @GetMapping(path = "/{id}")
    public UtenteRegistratoModel trovaUtenteRegistrato(@PathVariable("id") UUID id)
    {
        return utenteRegistratoService.trovaUtenteRegistrato(id)
                .orElse(null);
    }

    @GetMapping("/utenti")
    public List<UtenteRegistratoModel> trovaUtentiRegistrati()
    {
        return utenteRegistratoService.trovaUtentiRegistrati();
    }

    @PostMapping(path = "/aggiorna/{id}")
    public int aggiornaUtenteRegistrato(@PathVariable("id") UUID id,
                                        @RequestBody UtenteRegistratoModel utenteAggiornato)
    {
        return utenteRegistratoService.aggiornaUtenteRegistrato(id, utenteAggiornato);
    }

    @GetMapping(path = "/controlla/username/{username}")
    public boolean controllaUsernameOccupato(@PathVariable("username") String username)
    {
        return utenteRegistratoService.controllaUsernameOccupato(username);
    }

    @GetMapping(path = "/controlla/email/{email}")
    public boolean controllaEmailOccupata(@PathVariable("email") String email)
    {
        return utenteRegistratoService.controllaEmailOccupata(email);
    }

    @PostMapping("/controlla/utente")
    public boolean controllaUtenteEsiste(@RequestBody UtenteRegistratoModel utente)
    {
        return utenteRegistratoService.controllaUtenteEsiste(utente);
    }

    @PostMapping("/id")
    public UUID ritornaIdUtenteRegistrato(@RequestBody UtenteRegistratoModel utente){
        return utenteRegistratoService.ritornaIdUtenteRegistrato(utente);
    }

    @GetMapping(path = "/credito/aggiungi/{id}/{creditoAggiunto}")
    public int aggiungiCredito(@PathVariable("id") UUID id, @PathVariable("creditoAggiunto") float creditoAggiunto)
    {
        return utenteRegistratoService.aggiungiCredito(id, creditoAggiunto);
    }

    @GetMapping(path = "/credito/{id}")
    public InfoCreditoModel infoCredito(@PathVariable("id") UUID id)
    {
        return utenteRegistratoService.infoCredito(id);
    }

    @GetMapping(path = "controlla/notifica/email/{id}")
    public boolean isNotificheEmailAbilitate(@PathVariable("id") UUID id) {
        return utenteRegistratoService.isNotificheEmailAbilitate(id);
    }

    @GetMapping(path = "controlla/notifica/sms/{id}")
    public boolean isNotificheSmsAbilitate(@PathVariable("id") UUID id)
    {
        return utenteRegistratoService.isNotificheSmsAbilitate(id);
    }
}
