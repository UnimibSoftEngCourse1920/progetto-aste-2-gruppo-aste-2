package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
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

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

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

    // Test trovaOfferta
    @Test
    public void whenTrovaOfferta_givenNonExistingOfferta_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<OffertaModel> offertaTrovata = Optional.empty();

        given(offertaService.trovaOfferta(id)).willReturn(offertaTrovata);

        mockMvc.perform(get("/api/offerta/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenTrovaOfferta_givenExistingOfferta_thenReturnJsonMapOfferta() throws Exception {
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idOfferente, "username", "email", "numeroTelefono", "password", 10.5f);

        Optional<OffertaModel> offertaTrovata =
                Optional.of(new OffertaModel(idOfferta, 3.14f, Date.valueOf(LocalDate.now()), offerente));

        given(offertaService.trovaOfferta(idOfferta)).willReturn(offertaTrovata);

        mockMvc.perform(get("/api/offerta/" + idOfferta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(offertaTrovata.get().getId().toString()))
                .andExpect(jsonPath("$.creditoOfferto").value(offertaTrovata.get().getCreditoOfferto()))
                .andExpect(jsonPath("$.dataOfferta").value(offertaTrovata.get().getDataOfferta().toString()))
                .andExpect(jsonPath("$.offerente.id").value(offertaTrovata.get().getOfferente().getId().toString()))
                .andExpect(jsonPath("$.offerente.username").value(offertaTrovata.get().getOfferente().getUsername()))
                .andExpect(jsonPath("$.offerente.email").value(offertaTrovata.get().getOfferente().getEmail()))
                .andExpect(jsonPath("$.offerente.numeroTelefono").value(offertaTrovata.get().getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$.offerente.password").value(offertaTrovata.get().getOfferente().getPassword()))
                .andExpect(jsonPath("$.offerente.credito").value(offertaTrovata.get().getOfferente().getCredito()));
    }

    // Test trovaTutteOfferte
    @Test
    public void whenTrovaTutteOfferte_givenNonExistingTutteOfferte_thenReturnEmptyJsonArray() throws Exception {
        List<OffertaModel> tutteOfferteTrovate = Collections.emptyList();

        given(offertaService.trovaTutteOfferte()).willReturn(tutteOfferteTrovate);

        mockMvc.perform(get("/api/offerta/offerte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaTutteOfferte_givenExistingTutteOfferte_thenReturnJsonArrayOfMapsTutteOfferte() throws Exception {
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idOfferente, "username", "email", "numeroTelefono", "password", 10.5f);

        List<OffertaModel> tutteOfferteTrovate =
                Collections.singletonList(new OffertaModel(idOfferta, 3.14f, Date.valueOf(LocalDate.now()), offerente));

        given(offertaService.trovaTutteOfferte()).willReturn(tutteOfferteTrovate);

        mockMvc.perform(get("/api/offerta/offerte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(tutteOfferteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(tutteOfferteTrovate.get(0).getCreditoOfferto()))
                .andExpect(jsonPath("$[0].dataOfferta").value(tutteOfferteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(tutteOfferteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(tutteOfferteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(tutteOfferteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(tutteOfferteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(tutteOfferteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(tutteOfferteTrovate.get(0).getOfferente().getCredito()));
    }

    // Test trovaTutteOfferteAsta
    @Test
    public void whenTrovaTutteOfferteAsta_givenNonExistingTutteOfferteAsta_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaTutteOfferteAsta_givenExistingTutteOfferteAsta_thenReturnJsonArrayOfMapsTutteOfferteAsta() throws Exception {

    }

    // Test trovaTutteOfferteUtente
    @Test
    public void whenTrovaTutteOfferteUtente_givenNonExistingTutteOfferteUtente_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaTutteOfferteUtente_givenExistingTutteOfferteUtente_thenReturnJsonArrayOfMapsTutteOfferteUtente() throws Exception {

    }

    // Test trovaTutteOfferteUtenteAsta
    @Test
    public void whenTrovaTutteOfferteUtenteAsta_givenNonExistingTutteOfferteUtenteAsta_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaTutteOfferteUtenteAsta_givenExistingTutteOfferteUtenteAsta_thenReturnJsonArrayOfMapsTutteOfferteUtenteAsta() throws Exception {

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