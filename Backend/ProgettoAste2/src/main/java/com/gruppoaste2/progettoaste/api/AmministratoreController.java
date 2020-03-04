package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.service.AmministratoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/amministratore")
@RestController
public class AmministratoreController {

    private final AmministratoreService amministratoreService;

    @Autowired
    public AmministratoreController(AmministratoreService amministratoreService) {
        this.amministratoreService = amministratoreService;
    }

    @PostMapping("/inserisci")
    public int inserisciAmministratore(@RequestBody AmministratoreModel amministratore)
    {
        return  amministratoreService.inserisciAmministratore(amministratore);
    }

    @GetMapping(path = "/{id}")
    public AmministratoreModel trovaAmministratore(@PathVariable("id") UUID id)
    {
        return amministratoreService.trovaAmministratore(id).orElse(null);
    }

    @GetMapping("/amministratori")
    public List<AmministratoreModel> trovaAmministratori()
    {
        return amministratoreService.trovaAmministratori();
    }

    @GetMapping(path = "/controlla/username/{user}")
    public boolean controllaUsernameOccupato(@PathVariable("user") String username)
    {
        return amministratoreService.controllaUsernameOccupato(username);
    }

    @GetMapping(path = "/elimina/{id}")
    public int eliminaAmministratore(@PathVariable("id") UUID id)
    {
        return amministratoreService.eliminaAmministratore(id);
    }

    @GetMapping(path = "/controlla/email/{email}")
    public boolean controllaEmailOccupata(@PathVariable("email") String email)
    {
        return amministratoreService.controllaEmailOccupata(email);
    }

    @GetMapping("/controlla/utente")
    public boolean controllaAmministratoreEsiste(@RequestBody AmministratoreModel amministratore)
    {
        return amministratoreService.controllaAmministratoreEsiste(amministratore);
    }

    @PostMapping("/aggiorna/{id}")
    public int aggiornaAmministratore(@PathVariable("id") UUID id, @RequestBody AmministratoreModel amministratoreAggiornato)
    {
        return amministratoreService.aggiornaAmministratore(id, amministratoreAggiornato);
    }

}
