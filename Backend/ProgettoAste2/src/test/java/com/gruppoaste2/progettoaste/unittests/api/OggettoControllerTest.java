package com.gruppoaste2.progettoaste.unittests.api;

import com.gruppoaste2.progettoaste.api.OggettoController;
import com.gruppoaste2.progettoaste.service.OggettoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OggettoController.class)
class OggettoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OggettoService oggettoService;

    // Test inserisciOggetto
    @Test
    void whenInserisciOggetto_givenExistingOggetto_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenInserisciOggetto_givenNonExistingOggetto_thenReturnJsonNumber1() throws Exception {

    }

    // Test aggiornaOggetto
    @Test
    void whenAggiornaOggetto_givenNonExistingOggetto_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenAggiornaOggetto_givenExistingOggetto_thenReturnJsonNumber1() throws Exception {

    }

    // Test trovaOggetto
    @Test
    void whenTrovaOggetto_givenNonExistingOggetto_thenReturnEmptyJson() throws Exception {

    }

    @Test
    void whenTrovaOggetto_givenExistingOggetto_thenReturnJsonMapOggetto() throws Exception {

    }

    // Test trovaOggetti
    @Test
    void whenTrovaOggetti_givenNonExistingOggetti_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    void whenTrovaOggetti_givenExistingOggetti_thenReturnJsonArrayOfMapsOggetti() throws Exception {

    }

    // Test trovaOggettiAsta
    @Test
    void whenTrovaOggettiAsta_givenNonExistingOggettiAsta_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    void whenTrovaOggettiAsta_givenExistingOggettiAsta_thenReturnJsonArrayOfMapsOggettiAsta() throws Exception {

    }

    // Test trovaOggettiRegistratiDaUtente
    @Test
    void whenTrovaOggettiRegistratiDaUtente_givenNonExistingOggettiRegistratiDaUtente_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    void whenTrovaOggettiRegistratiDaUtente_givenExistingOggettiRegistratiDaUtente_thenReturnJsonArrayOfMapsOggettiRegistratiDaUtente() throws Exception {

    }

    // Test trovaOggettiInCorsoAstaUtente
    @Test
    void whenTrovaOggettiInCorsoAstaUtente_givenNonExistingOggettiInCorsoAstaUtente_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    void whenTrovaOggettiInCorsoAstaUtente_givenExistingOggettiInCorsoAstaUtente_thenReturnJsonArrayOfMapsOggettiInCorsoAstaUtente() throws Exception {

    }

    // Test trovaOggettiVintiDaUtente
    @Test
    void whenTrovaOggettiVintiDaUtente_givenNonExistingOggettiVintiDaUtente_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    void whenTrovaOggettiVintiDaUtente_givenExistingOggettiVintiDaUtente_thenReturnJsonArrayOfMapsOggettiVintiDaUtente() throws Exception {

    }
}