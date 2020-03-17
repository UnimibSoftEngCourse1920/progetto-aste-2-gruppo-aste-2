package com.gruppoaste2.progettoaste.unittests.model;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.OggettoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OggettoModelTest {

    @DisplayName("OggettoModel Constructor and Getters Test")
    @Test
    void oggettoModelConstructorGettersTest() {
        UUID idOggetto = UUID.randomUUID();
        String nomeOggetto = "nomeOggetto";
        String descrizione = "descrizione";
        String urlImmagine = "urlImmagine";
        List<CategoriaModel> categorie = Collections.emptyList();

        OggettoModel oggetto = new OggettoModel(idOggetto, nomeOggetto, descrizione, urlImmagine, categorie);

        assertEquals(idOggetto, oggetto.getId(),
                "Getter oggetto.getId() should return UUID: " + idOggetto);
        assertEquals(nomeOggetto, oggetto.getNome(),
                "Getter oggetto.getNome() should return String: \"" + nomeOggetto + "\"");
        assertEquals(descrizione, oggetto.getDescrizione(),
                "Getter oggetto.getDescrizione() should return String: \"" + descrizione + "\"");
        assertEquals(urlImmagine, oggetto.getUrlImmagine(),
                "Getter oggetto.getUrlImmagine() should return String: \"" + urlImmagine + "\"");
        assertEquals(categorie, oggetto.getCategorie(),
                "Getter oggetto.getCategorie() should return categorie");
    }
}
