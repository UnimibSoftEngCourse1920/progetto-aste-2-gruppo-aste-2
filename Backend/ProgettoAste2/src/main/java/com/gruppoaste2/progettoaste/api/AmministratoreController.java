package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.service.AmministratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * La classe {@link RestController @RestController} {@code AmministratoreController}
 * rappresenta le funzionalità della classe {@code AmministratoreModel},
 * richiamabili esternamente tramite richieste HTTP.
 * <p>
 * L'URL dell'endpoint è definito tramite l'annotazione
 * {@code {@link RequestMapping @RequestMapping}("api/amministratore")}.
 * Le funzioni in questa classe sono accessibili tramite richieste HTTP.
 */
@RequestMapping("api/amministratore")
@RestController
public class AmministratoreController {

    private final AmministratoreService amministratoreService;

    @Autowired
    public AmministratoreController(AmministratoreService amministratoreService) {
        this.amministratoreService = amministratoreService;
    }

    @PostMapping("/inserisci")
    public UUID inserisciAmministratore(@RequestBody AmministratoreModel amministratore)
    {
        return amministratoreService.inserisciAmministratore(amministratore);
    }

    @GetMapping(path = "/elimina/{id}")
    public int eliminaAmministratore(@PathVariable("id") UUID idAmministratore)
    {
        return amministratoreService.eliminaAmministratore(idAmministratore);
    }

    @GetMapping(path = "/{idAmministratore}")
    public AmministratoreModel trovaAmministratore(@PathVariable("idAmministratore") UUID idAmministratore)
    {
        return amministratoreService.trovaAmministratore(idAmministratore)
                .orElse(null);
    }

    @GetMapping("/amministratori")
    public List<AmministratoreModel> trovaAmministratori()
    {
        return amministratoreService.trovaAmministratori();
    }

    @PostMapping("/aggiorna/{idAmministratore}")
    public int aggiornaAmministratore(@PathVariable("idAmministratore") UUID idAmministratore,
                                      @RequestBody AmministratoreModel amministratoreAggiornato)
    {
        return amministratoreService.aggiornaAmministratore(idAmministratore, amministratoreAggiornato);
    }

    @GetMapping(path = "/controlla/username/{username}")
    public boolean controllaUsernameOccupato(@PathVariable("username") String username)
    {
        return amministratoreService.controllaUsernameOccupato(username);
    }

    @GetMapping(path = "/controlla/email/{email}")
    public boolean controllaEmailOccupata(@PathVariable("email") String email)
    {
        return amministratoreService.controllaEmailOccupata(email);
    }

    @PostMapping("/controlla/amministratore")
    public boolean controllaAmministratoreEsiste(@RequestBody AmministratoreModel amministratore)
    {
        return amministratoreService.controllaAmministratoreEsiste(amministratore);
    }

    @PostMapping("/id")
    public UUID ritornaIdAmministratore(@RequestBody AmministratoreModel amministratore)
    {
        return amministratoreService.ritornaIdAmministratore(amministratore);
    }
}
