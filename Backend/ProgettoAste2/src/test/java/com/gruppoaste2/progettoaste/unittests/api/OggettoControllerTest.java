package com.gruppoaste2.progettoaste.unittests.api;

import com.gruppoaste2.progettoaste.api.OggettoController;
import com.gruppoaste2.progettoaste.service.OggettoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(OggettoController.class)
public class OggettoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OggettoService oggettoService;

    // Test inserisciOggetto
    @Test
    public void whenInserisciOggetto_givenExistingOggetto_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenInserisciOggetto_givenNonExistingOggetto_thenReturnJsonNumber1() throws Exception {

    }

    // Test aggiornaOggetto
    @Test
    public void whenAggiornaOggetto_givenNonExistingOggetto_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenAggiornaOggetto_givenExistingOggetto_thenReturnJsonNumber1() throws Exception {

    }

    // Test trovaOggetto
    @Test
    public void whenTrovaOggetto_givenNonExistingOggetto_thenReturnEmptyJson() throws Exception {

    }

    @Test
    public void whenTrovaOggetto_givenExistingOggetto_thenReturnJsonMapOggetto() throws Exception {

    }

    // Test trovaOggetti
    @Test
    public void whenTrovaOggetti_givenNonExistingOggetti_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaOggetti_givenExistingOggetti_thenReturnJsonArrayOfMapsOggetti() throws Exception {

    }

    // Test trovaOggettiAsta
    @Test
    public void whenTrovaOggettiAsta_givenNonExistingOggettiAsta_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaOggettiAsta_givenExistingOggettiAsta_thenReturnJsonArrayOfMapsOggettiAsta() throws Exception {

    }

    // Test trovaOggettiRegistratiDaUtente
    @Test
    public void whenTrovaOggettiRegistratiDaUtente_givenNonExistingOggettiRegistratiDaUtente_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaOggettiRegistratiDaUtente_givenExistingOggettiRegistratiDaUtente_thenReturnJsonArrayOfMapsOggettiRegistratiDaUtente() throws Exception {

    }

    // Test trovaOggettiInCorsoAstaUtente
    @Test
    public void whenTrovaOggettiInCorsoAstaUtente_givenNonExistingOggettiInCorsoAstaUtente_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaOggettiInCorsoAstaUtente_givenExistingOggettiInCorsoAstaUtente_thenReturnJsonArrayOfMapsOggettiInCorsoAstaUtente() throws Exception {

    }

    // Test trovaOggettiVintiDaUtente
    @Test
    public void whenTrovaOggettiVintiDaUtente_givenNonExistingOggettiVintiDaUtente_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaOggettiVintiDaUtente_givenExistingOggettiVintiDaUtente_thenReturnJsonArrayOfMapsOggettiVintiDaUtente() throws Exception {

    }
}