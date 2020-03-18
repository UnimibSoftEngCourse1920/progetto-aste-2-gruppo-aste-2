package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * La classe {@link RestController @RestController} {@code CategoriaController}
 * rappresenta le funzionalità della classe {@code CategoriaModel},
 * richiamabili esternamente tramite richieste HTTP.
 * <p>
 * L'URL dell'endpoint è definito tramite l'annotazione
 * {@code {@link RequestMapping @RequestMapping}("api/categoria")}.
 * Le funzioni in questa classe sono accessibili tramite richieste HTTP.
 */
@RequestMapping("api/categoria")
@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/aggiungi")
    public String aggiungiCategoria(@RequestBody CategoriaModel categoria)
    {
        return categoriaService.aggiungiCategoria(categoria);
    }

    @GetMapping(path = "/elimina/{idCategoria}")
    public int eliminaCategoria(@PathVariable("idCategoria") String idCategoria)
    {
        return categoriaService.eliminaCategoria(idCategoria);
    }

    @GetMapping(path = "/{idCategoria}")
    public CategoriaModel trovaCategoria(@PathVariable("idCategoria") String idCategoria)
    {
        return categoriaService.trovaCategoria(idCategoria)
                .orElse(null);
    }

    @GetMapping("/categorie")
    public List<CategoriaModel> trovaCategorie()
    {
        return categoriaService.trovaCategorie();
    }

    @GetMapping(path = "categorie/oggetto/{idOggetto}")
    public List<CategoriaModel> trovaCategorieOggetto(@PathVariable("idOggetto") UUID idOggetto) {
        return categoriaService.trovaCategorieOggetto(idOggetto);
    }

    @PostMapping(path = "/aggiorna/{idCategoria}")
    public int aggiornaCategoria(@PathVariable("idCategoria") String idCategoria,
                                 @RequestBody CategoriaModel categoriaAggiornata)
    {
        return categoriaService.aggiornaCategoria(idCategoria, categoriaAggiornata);
    }

    @PostMapping(path = "/controlla/categoria")
    public boolean controllaCategoriaEsiste(@RequestBody CategoriaModel categoria)
    {
        return categoriaService.controllaCategoriaEsiste(categoria);
    }

    @GetMapping("/assegna/categoria/{idOggetto}/{idCategoria}")
    public int assegnaCategoriaAdOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                         @PathVariable("idCategoria") String idCategoria) {
        return categoriaService.assegnaCategoriaAdOggetto(idOggetto, idCategoria);
    }

    @GetMapping("/rimuovi/categoria/{idOggetto}/{idCategoria}")
    public int rimuoviCategoriaDaOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                         @PathVariable("idCategoria") String idCategoria) {
        return categoriaService.rimuoviCategoriaDaOggetto(idOggetto, idCategoria);
    }
}
