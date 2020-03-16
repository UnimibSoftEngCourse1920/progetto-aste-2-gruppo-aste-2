package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import com.gruppoaste2.progettoaste.service.ConfigurazioneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/configurazione")
@RestController
public class ConfigurazioneController {

    private final ConfigurazioneService configurazioneService;

    public ConfigurazioneController(ConfigurazioneService configurazioneService) {
        this.configurazioneService = configurazioneService;
    }

    @PostMapping("/inserisci")
    public UUID inserisciConfigurazione(@RequestBody ConfigurazioneModel configurazioneModel){
        return configurazioneService.inserisciConfigurazione(configurazioneModel);
    }

    @GetMapping(path = "/elimina/{idConfigurazione}")
    public int  eliminaConfiguazione(@PathVariable("idConfigurazione") UUID idConfigurazione){
        return configurazioneService.eliminaConfiguazione(idConfigurazione);
    }

    @GetMapping(path = "/{idConfigurazione}")
    public ConfigurazioneModel trovaConfigurazione(@PathVariable("idConfigurazione") UUID idConfigurazione) {
        return configurazioneService.trovaConfigurazione(idConfigurazione)
                .orElse(null);
    }

    @GetMapping("/configurazioni")
    public List<ConfigurazioneModel> trovaConfigurazioni(){
        return configurazioneService.trovaConfigurazioni();
    }

    @GetMapping("/configurazioni/ultima")
    public Optional<ConfigurazioneModel> trovaUltimaConfigurazione()
    {
        return configurazioneService.trovaUltimaConfigurazione();
    }
}
