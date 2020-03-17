package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/categoria")
@RestController
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/aggiungi")
    public UUID aggiungiCategoria(@RequestBody CategoriaModel categoria)
    {
        return categoriaService.aggiungiCategoria(categoria);
    }

    @PostMapping(path = "/aggiungi/attributo/{idCategoria}")
    public UUID aggiungiAttributoCategoria(@PathVariable("idCategoria") UUID idCategoria,
                                           @RequestBody AttributoModel attributo)
    {
        return categoriaService.aggiungiAttributoCategoria(idCategoria, attributo);
    }

    @GetMapping(path = "/elimina/{idCategoria}")
    public int eliminaCategoria(@PathVariable("idCategoria") UUID idCategoria)
    {
        return categoriaService.eliminaCategoria(idCategoria);
    }

    @GetMapping(path = "/elimina/attributi/{idCategoria}")
    public int eliminaAttributiCategoria(@PathVariable("idCategoria") UUID idCategoria)
    {
        return categoriaService.eliminaAttributiCategoria(idCategoria);
    }

    @GetMapping(path = "/{idCategoria}")
    public CategoriaModel trovaCategoria(@PathVariable("idCategoria") UUID idCategoria)
    {
        return categoriaService.trovaCategoria(idCategoria)
                .orElse(null);
    }

    @GetMapping("/categorie")
    public List<CategoriaModel> trovaCategorie()
    {
        return categoriaService.trovaCategorie();
    }

    @GetMapping(path = "/attributi/{idCategoria}")
    public List<AttributoModel> trovaAttributiCategoria(@PathVariable("idCategoria") UUID idCategoria) {
        return categoriaService.trovaAttributiCategoria(idCategoria);
    }

    @GetMapping(path = "/oggetto/{idOggetto}")
    public List<CategoriaModel> trovaCategorieOggetto(@PathVariable("idOggetto") UUID idOggetto) {
        return categoriaService.trovaCategorieOggetto(idOggetto);
    }

    @GetMapping("/attributo/{idOggetto}/oggetto/{idAttributo}")
    public String valoreAttributoOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                         @PathVariable("idAttributo") UUID idAttributo){
        return categoriaService.valoreAttributoOggetto(idOggetto, idAttributo);
    }

    @PostMapping(path = "/aggiorna/{idCategoria}")
    public int aggiornaCategoria(@PathVariable("idCategoria") UUID idCategoria,
                                 @RequestBody CategoriaModel categoriaAggiornata)
    {
        return categoriaService.aggiornaCategoria(idCategoria, categoriaAggiornata);
    }

    @GetMapping("/assegna/categoria/{idOggetto}/{idCategoria}")
    public int assegnaCategoriaAdOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                         @PathVariable("idCategoria") UUID idCategoria) {
        return categoriaService.assegnaCategoriaAdOggetto(idOggetto, idCategoria);
    }

    @GetMapping("/rimuovi/categoria/{idOggetto}/{idCategoria}")
    public int rimuoviCategoriaDaOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                         @PathVariable("idCategoria") UUID idCategoria) {
        return categoriaService.rimuoviCategoriaDaOggetto(idOggetto, idCategoria);
    }

    @PostMapping("/assegna/attributo/{idOggetto}")
    public int assegnaValoreAttributoAdOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                               @RequestBody AttributoModel attributo) {
        return categoriaService.assegnaValoreAttributoAdOggetto(idOggetto, attributo);
    }

    @GetMapping("/rimuovi/attributo/{idOggetto}/{idAttributo}")
    public int rimuoviValoreAttributoDaOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                               @PathVariable("idAttributo") UUID idAttributo) {
        return categoriaService.rimuoviValoreAttributoDaOggetto(idOggetto, idAttributo);
    }
}
