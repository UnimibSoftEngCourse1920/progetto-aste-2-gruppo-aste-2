package com.gruppoaste2.progettoaste.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/utenteregistrato")
@RestController
public class UtenteRegistratoController {
    @GetMapping
    public String esempioGet()
    {
        return "<h1>Prova ciao hai fatto una get ad localhost:8080/api/utenteregistrato<h1>";
    }
}
