package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.service.AttributoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * La classe {@link RestController @RestController} {@code AttributoController}
 * rappresenta le funzionalità della classe {@code AttributoModel},
 * richiamabili esternamente tramite richieste HTTP.
 * <p>
 * L'URL dell'endpoint è definito tramite l'annotazione
 * {@code {@link RequestMapping @RequestMapping}("api/attributo")}.
 * Le funzioni in questa classe sono accessibili tramite richieste HTTP.
 */
@RequestMapping("api/attributo")
@RestController
public class AttributoController {

    private final AttributoService attributoService;

    @Autowired
    public AttributoController(AttributoService attributoService) {
        this.attributoService = attributoService;
    }

    @PostMapping(path = "/aggiungi/{idCategoria}")
    public UUID aggiungiAttributo(@PathVariable("idCategoria") String idCategoria,
                                  @RequestBody AttributoModel attributo)
    {
        return attributoService.aggiungiAttributo(idCategoria, attributo);
    }

    @GetMapping(path = "/elimina/{idAttributo}")
    public int eliminaAttributo(@PathVariable("idAttributo") UUID idAttributo)
    {
        return attributoService.eliminaAttributo(idAttributo);
    }

    @GetMapping(path = "/{idAttributo}")
    public AttributoModel trovaAttributo(@PathVariable("idAttributo") UUID idAttributo)
    {
        return attributoService.trovaAttributo(idAttributo)
                .orElse(null);
    }

    @GetMapping("/attributi")
    public List<AttributoModel> trovaAttributi()
    {
        return attributoService.trovaAttributi();
    }

    @GetMapping(path = "/attributi/categoria/{idCategoria}")
    public List<AttributoModel> trovaAttributiCategoria(@PathVariable("idCategoria") String idCategoria) {
        return attributoService.trovaAttributiCategoria(idCategoria);
    }

    @GetMapping("/attributi/oggetto/{idOggetto}")
    public List<AttributoModel> trovaAttributiOggetto(@PathVariable("idOggetto") UUID idOggetto) {
        return attributoService.trovaAttributiOggetto(idOggetto);
    }

    @PostMapping(path = "/aggiorna/{idAttributo}")
    public int aggiornaAttributo(@PathVariable("idAttributo") UUID idAttributo,
                                 @RequestBody AttributoModel attributoAggiornato)
    {
        return attributoService.aggiornaAttributo(idAttributo, attributoAggiornato);
    }

    @PostMapping(path = "/aggiorna/oggetto/{idAttributo}/{idOggetto}")
    public int aggiornaAttributoOggetto(@PathVariable("idAttributo") UUID idAttributo,
                                        @PathVariable("idOggetto") UUID idOggetto,
                                        @RequestBody AttributoModel attributoAggiornato)
    {
        return attributoService.aggiornaAttributoOggetto(idAttributo, idOggetto, attributoAggiornato);
    }

    @PostMapping("/assegna/{idOggetto}")
    public int assegnaValoreAttributoAdOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                               @RequestBody AttributoModel attributo) {
        return attributoService.assegnaValoreAttributoAdOggetto(idOggetto, attributo);
    }

    @GetMapping("/rimuovi/{idOggetto}/{idAttributo}")
    public int rimuoviValoreAttributoDaOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                               @PathVariable("idAttributo") UUID idAttributo) {
        return attributoService.rimuoviValoreAttributoDaOggetto(idOggetto, idAttributo);
    }
}
