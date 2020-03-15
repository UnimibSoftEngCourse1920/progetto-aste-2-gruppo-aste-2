package com.gruppoaste2.progettoaste.unittests.model;

import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OggettoModelTest {

    @DisplayName("OggettoModel Constructor and Getters Test")
    @Test
    void oggettoModelConstructorGettersTest()
    {
        UUID id = UUID.randomUUID();
        String nome = "nome";
        String descrizione = "descrizione";
        String urlImmagine = "urlImmagine";
        OggettoModel oggetto = new OggettoModel(id, nome, descrizione, urlImmagine);

        assertEquals(id, oggetto.getId(),
                "Getter oggetto.getId() should return UUID: " + id);
        assertEquals(nome, oggetto.getNome(),
                "Getter oggetto.getNome() should return String: \"" + nome + "\"");
        assertEquals(descrizione, oggetto.getDescrizione(),
                "Getter oggetto.getDescrizione() should return String: \"" + descrizione + "\"");
        assertEquals(urlImmagine, oggetto.getUrlImmagine(),
                "Getter oggetto.getUrlImmagine() should return String: \"" + urlImmagine + "\"");
    }
}
