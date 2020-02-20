package com.gruppoaste2.progettoaste.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.net.URL;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OggettoModelTest {

    @DisplayName("Test OggettoModel")
    @Test
    void testOggettoModel()
    {
        UUID provaId = UUID.randomUUID();
        OggettoModel oggettoTest = new OggettoModel(provaId, "nome", "Descrizione","http://www.example.com/docs/resource1.html");
        assertEquals(oggettoTest.getNome(),"nome", "Nome deve essere 'nome'");
        assertEquals(oggettoTest.getDescrizione(),"Descrizione","Descrizione deve essere 'Descrizione'");
        assertEquals(oggettoTest.getUrlImmagine(),"http://www.example.com/docs/resource1.html", "url sbagliato");
        assertEquals(oggettoTest.getId().toString(), provaId.toString(), "Id errato");
    }
}