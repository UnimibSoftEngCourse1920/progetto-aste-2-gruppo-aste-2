package com.gruppoaste2.progettoaste.api;


import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.UtenteRegistratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/utenteregistrato")
@RestController
public class UtenteRegistratoController {

    private final UtenteRegistratoService utenteRegistratoService;

    @Autowired
    public UtenteRegistratoController(UtenteRegistratoService utenteRegistratoService) {
        this.utenteRegistratoService = utenteRegistratoService;
    }

    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/utenteregistrato<h1>";
    }

    @GetMapping("/utenti")
    public List<UtenteRegistratoModel> trovaUtentiRegistrati()
    {
        return utenteRegistratoService.trovaTuttiUtentiRegistrati();
    }
}
