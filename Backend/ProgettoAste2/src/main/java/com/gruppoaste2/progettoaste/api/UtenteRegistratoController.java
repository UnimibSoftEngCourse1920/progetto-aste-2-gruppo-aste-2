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

    @PostMapping("/aggiungi")
    public UUID aggiungiUtenteRegistrato(@RequestBody UtenteRegistratoModel utente)
    {
        return utenteRegistratoService.aggiungiUtenteRegistrato(utente);
    }

    @GetMapping(path = "/elimina/{idUtenteRegistrato}")
    public int eliminaUtenteRegistrato(@PathVariable("idUtenteRegistrato") UUID idUtenteRegistrato)
    {
        return utenteRegistratoService.eliminaUtenteRegistrato(idUtenteRegistrato);
    }

    @GetMapping(path = "/{idUtenteRegistrato}")
    public UtenteRegistratoModel trovaUtenteRegistrato(@PathVariable("idUtenteRegistrato") UUID idUtenteRegistrato)
    {
        return utenteRegistratoService.trovaUtenteRegistrato(idUtenteRegistrato)
                .orElse(null);
    }

    @GetMapping("/utenti")
    public List<UtenteRegistratoModel> trovaUtentiRegistrati()
    {
        return utenteRegistratoService.trovaUtentiRegistrati();
    }

    @PostMapping(path = "/aggiorna/{idUtenteRegistrato}")
    public int aggiornaUtenteRegistrato(@PathVariable("idUtenteRegistrato") UUID idUtenteRegistrato,
                                        @RequestBody UtenteRegistratoModel utenteRegistratoAggiornato)
    {
        return utenteRegistratoService.aggiornaUtenteRegistrato(idUtenteRegistrato, utenteRegistratoAggiornato);
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

    @GetMapping(path = "/credito/aggiungi/{idUtenteRegistrato}/{creditoAggiunto}")
    public int aggiungiCredito(@PathVariable("idUtenteRegistrato") UUID idUtenteRegistrato,
                               @PathVariable("creditoAggiunto") float creditoAggiunto)
    {
        return utenteRegistratoService.aggiungiCredito(idUtenteRegistrato, creditoAggiunto);
    }

    @GetMapping(path = "/credito/{idUtenteRegistrato}")
    public InfoCreditoModel infoCredito(@PathVariable("idUtenteRegistrato") UUID idUtenteRegistrato)
    {
        return utenteRegistratoService.infoCredito(idUtenteRegistrato);
    }

    @GetMapping(path = "controlla/notifica/email/{idUtenteRegistrato}")
    public boolean isNotificheEmailAbilitate(@PathVariable("idUtenteRegistrato") UUID idUtenteRegistrato) {
        return utenteRegistratoService.isNotificheEmailAbilitate(idUtenteRegistrato);
    }

    @GetMapping(path = "controlla/notifica/sms/{idUtenteRegistrato}")
    public boolean isNotificheSmsAbilitate(@PathVariable("idUtenteRegistrato") UUID idUtenteRegistrato)
    {
        return utenteRegistratoService.isNotificheSmsAbilitate(idUtenteRegistrato);
    }
}
