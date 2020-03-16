package com.gruppoaste2.progettoaste.unittests.model;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
        List<CategoriaModel> categorie = Collections.emptyList();
        OggettoModel oggetto = new OggettoModel(id, nome, descrizione, urlImmagine, categorie);
//  todo andre la roba empty magari sostituisci con un test piu serio
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
