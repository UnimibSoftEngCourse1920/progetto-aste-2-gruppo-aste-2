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

    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/categoria<h1>";
    }

    @GetMapping("/categorie")
    public List<CategoriaModel> trovaCategorie()
    {
        return categoriaService.trovaCategorie();
    }

    @GetMapping(path="{id}")
    public CategoriaModel trovaCategoria(@PathVariable("id") UUID id)
    {
        return categoriaService.trovaCategoria(id)
                .orElse(null);
    }

    @PostMapping("/aggiungi")
    public int aggiungiCategoria(@RequestBody CategoriaModel categoria)
    {
        return categoriaService.inserisciCategoria(categoria);
    }

    @PostMapping(path = "/aggiorna/{id}")
    public int aggiornaCategoria(@PathVariable("id") UUID id, @RequestBody CategoriaModel categoriaAggiornata)
    {
        return categoriaService.aggiornaCategoria(id, categoriaAggiornata);
    }

    @GetMapping(path = "/elimina/{id}") // funziona
    public int eliminaCategoria(@PathVariable("id") UUID id)
    {
        return categoriaService.eliminaCategoria(id);
    }

    @GetMapping(path = "/attributi/{id}")
    public List<AttributoModel> trovaAttributiCategoria(@PathVariable("id") UUID id){
        return categoriaService.trovaAttributiCategoria(id);
    }

    @GetMapping(path = "/oggetto/{id}")
    public List<CategoriaModel> trovaCategorieOggetto(@PathVariable("id") UUID idOggetto) {
        return categoriaService.trovaCategorieOggetto(idOggetto);
    }

    @GetMapping("/attributo/{idOggetto}/oggetto/{idAttributo}")
    public String valoreAttributoOggetto(@PathVariable("idOggetto") UUID idOggetto,@PathVariable("idAttributo") UUID idAttributo){
        return categoriaService.valoreAttributoOggetto(idOggetto, idAttributo);
    }

}
