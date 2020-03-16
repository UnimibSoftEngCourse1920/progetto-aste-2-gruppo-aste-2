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

    @GetMapping("/assegna/{idOggetto}/{idCategoria}")
    public int assegnaCategoriaAdOggetto(@PathVariable("idOggetto") UUID idOggetto, @PathVariable("idCategoria") UUID idCategoria) {
        return categoriaService.assegnaCategoriaAdOggetto(idOggetto, idCategoria);
    }

    @GetMapping(path = "/elimina/{id}")
    public int eliminaCategoria(@PathVariable("id") UUID id)
    {
        return categoriaService.eliminaCategoria(id);
    }

    @GetMapping(path = "/{id}")
    public CategoriaModel trovaCategoria(@PathVariable("id") UUID id)
    {
        return categoriaService.trovaCategoria(id)
                .orElse(null);
    }

    @GetMapping("/categorie")
    public List<CategoriaModel> trovaCategorie()
    {
        return categoriaService.trovaCategorie();
    }

    @GetMapping(path = "/attributi/{id}")
    public List<AttributoModel> trovaAttributiCategoria(@PathVariable("id") UUID id) {
        return categoriaService.trovaAttributiCategoria(id);
    }

    @GetMapping(path = "/oggetto/{id}")
    public List<CategoriaModel> trovaCategorieOggetto(@PathVariable("id") UUID idOggetto) {
        return categoriaService.trovaCategorieOggetto(idOggetto);
    }

    @GetMapping("/attributo/{idOggetto}/oggetto/{idAttributo}")
    public String valoreAttributoOggetto(@PathVariable("idOggetto") UUID idOggetto,
                                         @PathVariable("idAttributo") UUID idAttributo){
        return categoriaService.valoreAttributoOggetto(idOggetto, idAttributo);
    }

    @PostMapping(path = "/aggiorna/{id}")
    public int aggiornaCategoria(@PathVariable("id") UUID id, @RequestBody CategoriaModel categoriaAggiornata)
    {
        return categoriaService.aggiornaCategoria(id, categoriaAggiornata);
    }
}
