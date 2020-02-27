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

    private  final ConfigurazioneService configurazioneService;

    public ConfigurazioneController(ConfigurazioneService configurazioneService) {
        this.configurazioneService = configurazioneService;
    }

    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/configurazione<h1>";
    }

    @PostMapping("/inserisci")
    public int inserisciConfigurazione(@RequestBody ConfigurazioneModel configurazioneModel){
        return configurazioneService.inserisciConfigurazione(configurazioneModel); //controllare id qui
    }

    @GetMapping(path = "elimina/{id}")
    public int  eliminaConfiguazione(@PathVariable("id") UUID id){
        return configurazioneService.eliminaConfiguazione(id);
    }

    @GetMapping(path = "/{id}")
    public Optional<ConfigurazioneModel> trovaConfigurazione(@PathVariable("id") UUID id) {
        return configurazioneService.trovaConfigurazione(id);
    }

    @GetMapping("/configurazioni")
    public Optional<List<ConfigurazioneModel>> trovaConfigurazioni(){
        return configurazioneService.trovaConfigurazioni();
    }

     @PostMapping("/configurazione")
     public boolean esisteConfSimile (@RequestBody ConfigurazioneModel configurazioneModel){
        return configurazioneService.esisteConfSimile(configurazioneModel);
    }
}