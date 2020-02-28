package com.gruppoaste2.progettoaste.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(path = "elimina/{id}") // funziona
    public int eliminaCategoria(@PathVariable("id") UUID id)
    {
        return categoriaService.eliminaCategoria(id);
    }

}
