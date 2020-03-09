package com.gruppoaste2.progettoaste.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OggettoModelTest {

    @DisplayName("Test OggettoModel")
    @Test
    void testOggettoModel()
    {
        UUID provaId = UUID.randomUUID();
        OggettoModel oggettoTest = new OggettoModel(provaId, "nome", "Descrizione","http://www.example.com/docs/resource1.html");
        assertEquals("nome", oggettoTest.getNome(),"Nome deve essere 'nome'");
        assertEquals("Descrizione",oggettoTest.getDescrizione(),"Descrizione deve essere 'Descrizione'");
        assertEquals("http://www.example.com/docs/resource1.html", oggettoTest.getUrlImmagine(), "url sbagliato");
        assertEquals(provaId.toString(), oggettoTest.getId().toString(),"Id errato");
    }
}