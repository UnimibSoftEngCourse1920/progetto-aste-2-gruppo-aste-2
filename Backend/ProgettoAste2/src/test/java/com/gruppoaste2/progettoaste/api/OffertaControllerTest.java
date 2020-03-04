package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.OffertaModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.OffertaService;
import com.gruppoaste2.progettoaste.service.UtenteRegistratoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OffertaController.class)
class OffertaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OffertaService offertaService;

    @Test
    void esempioGet() {
    }

    // Test trovaOfferta
    @Test
    public void whenTrovaOfferta_givenNotExistingOfferta_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<OffertaModel> offertaTrovata = Optional.ofNullable(null);

        given(offertaService.trovaOfferta(id)).willReturn(offertaTrovata);

        mockMvc.perform(get("/api/offerta/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenTrovaOfferta_givenExistingOfferta_thenReturnJsonOfferta() throws Exception {

    }

    // Test trovaTutteOfferte
    @Test
    public void whenTrovaTutteOfferte_givenNotExistingTutteOfferte_thenReturnEmptyJson() throws Exception {
        List<OffertaModel> tutteOfferteTrovate = Arrays.asList();

        given(offertaService.trovaTutteOfferte()).willReturn(tutteOfferteTrovate);

        mockMvc.perform(get("/api/offerta/offerte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaTutteOfferte_givenExistingTutteOfferte_thenReturnJsonArray() throws Exception {

    }

    // Test trovaTutteOfferteAsta
    @Test
    public void whenTrovaTutteOfferteAsta_givenNotExistingTutteOfferteAsta_thenReturnEmptyJson() throws Exception {

    }

    @Test
    public void whenTrovaTutteOfferteAsta_givenExistingTutteOfferteAsta_thenReturnJsonArray() throws Exception {

    }

    // Test trovaTutteOfferteUtente
    @Test
    public void whenTrovaTutteOfferteUtente_givenNotExistingTutteOfferteUtente_thenReturnEmptyJson() throws Exception {

    }

    @Test
    public void whenTrovaTutteOfferteUtente_givenExistingTutteOfferteUtente_thenReturnJsonArray() throws Exception {

    }

    // Test trovaTutteOfferteUtenteAsta
    @Test
    public void whenTrovaTutteOfferteUtenteAsta_givenNotExistingTutteOfferteUtenteAsta_thenReturnEmptyJson() throws Exception {

    }

    @Test
    public void whenTrovaTutteOfferteUtenteAsta_givenExistingTutteOfferteUtenteAsta_thenReturnJsonArray() throws Exception {

    }

    // Test aggiungiOfferta
    @Test
    public void whenAggiungiOfferta_givenExistingOfferta_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenAggiungiOfferta_givenNonExistingOfferta_thenReturnJsonNumber1() throws Exception {

    }

    // Test aggiornaOfferta
    @Test
    public void whenAggiornaOfferta_givenNonExistingOfferta_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenAggiornaOfferta_givenExistingOfferta_thenReturnJsonNumber1() throws Exception {

    }

    // Test eliminaOfferta
    @Test
    public void whenEliminaConfigurazione_givenNonExistingConfigurazione_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(offertaService.eliminaOfferta(id)).willReturn(0);

        mockMvc.perform(get("/api/offerta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    public void whenEliminaConfigurazione_givenExistingConfigurazione_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(offertaService.eliminaOfferta(id)).willReturn(1);

        mockMvc.perform(get("/api/offerta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }
}