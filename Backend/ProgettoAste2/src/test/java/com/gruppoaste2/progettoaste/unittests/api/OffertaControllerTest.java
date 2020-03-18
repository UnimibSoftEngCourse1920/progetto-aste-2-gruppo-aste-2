package com.gruppoaste2.progettoaste.unittests.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruppoaste2.progettoaste.api.OffertaController;
import com.gruppoaste2.progettoaste.model.*;
import com.gruppoaste2.progettoaste.service.OffertaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OffertaController.class)
class OffertaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OffertaService offertaService;

    // Test inserisciOfferta
    /*
    @DisplayName("inserisciOfferta non inserisce Offerta")
    @Test
    void whenInserisciOfferta_givenAlreadyExistingOfferta_thenReturnEmptyJson() throws Exception

    @DisplayName("inserisciOfferta inserisce Offerta")
    @Test
    void whenInserisciOfferta_givenNonExistingOfferta_thenReturnJsonStringIdOfferta()
            throws Exception
    */

    // Test eliminaOfferta
    @DisplayName("eliminaOfferta non elimina Offerta")
    @Test
    void whenEliminaOfferta_givenNonExistingOfferta_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(offertaService.eliminaOfferta(id)).willReturn(0);

        mockMvc.perform(get("/api/offerta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("eliminaOfferta elimina Offerta")
    @Test
    void whenEliminaOfferta_givenExistingOfferta_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(offertaService.eliminaOfferta(id)).willReturn(1);

        mockMvc.perform(get("/api/offerta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test trovaOfferta
    @DisplayName("trovaOfferta non trova Offerta")
    @Test
    void whenTrovaOfferta_givenNonExistingOfferta_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<OffertaModel> offertaTrovata = Optional.empty();

        given(offertaService.trovaOfferta(id)).willReturn(offertaTrovata);

        mockMvc.perform(get("/api/offerta/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("trovaOfferta trova Offerta")
    @Test
    void whenTrovaOfferta_givenExistingOfferta_thenReturnJsonMapOfferta() throws Exception {
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idOfferente, "username", "email",
                        "numeroTelefono", "password", 10.5f, false,
                        false);

        Optional<OffertaModel> offertaTrovata =
                Optional.of(new OffertaModel(idOfferta, 3.14f, Timestamp.valueOf(LocalDateTime.now()),
                        offerente));

        given(offertaService.trovaOfferta(idOfferta)).willReturn(offertaTrovata);

        mockMvc.perform(get("/api/offerta/" + idOfferta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(offertaTrovata.get().getId().toString()))
                .andExpect(jsonPath("$.creditoOfferto").value(offertaTrovata.get().getCreditoOfferto()))
                //.andExpect(jsonPath("$.dataOfferta").value(offertaTrovata.get().getDataOfferta().toString()))
                .andExpect(jsonPath("$.offerente.id").value(offertaTrovata.get().getOfferente().getId().toString()))
                .andExpect(jsonPath("$.offerente.username").value(offertaTrovata.get().getOfferente().getUsername()))
                .andExpect(jsonPath("$.offerente.email").value(offertaTrovata.get().getOfferente().getEmail()))
                .andExpect(jsonPath("$.offerente.numeroTelefono").value(offertaTrovata.get().getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$.offerente.password").value(offertaTrovata.get().getOfferente().getPassword()))
                .andExpect(jsonPath("$.offerente.credito").value(offertaTrovata.get().getOfferente().getCredito()));
    }

    // Test trovaOfferte
    @DisplayName("trovaOfferte non trova Offerte")
    @Test
    void whenTrovaOfferte_givenNonExistingOfferte_thenReturnEmptyJsonArray() throws Exception {
        List<OffertaModel> offerteTrovate = Collections.emptyList();

        given(offertaService.trovaOfferte()).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("trovaOfferte trova Offerte")
    @Test
    void whenTrovaOfferte_givenExistingOfferte_thenReturnJsonArrayOfMapsOfferte() throws Exception {
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idOfferente, "username", "email",
                        "numeroTelefono", "password", 10.5f, false,
                        false);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idOfferta, 3.14f,
                        Timestamp.valueOf(LocalDateTime.now()), offerente));

        given(offertaService.trovaOfferte()).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                //.andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
    }

    // Test trovaOfferteAsta
    @DisplayName("trovaOfferteAsta non trova OfferteAsta")
    @Test
    void whenTrovaOfferteAsta_givenNonExistingOfferteAsta_thenReturnEmptyJsonArray() throws Exception {
        UUID idAsta = UUID.randomUUID();

        List<OffertaModel> offerteTrovate = Collections.emptyList();

        given(offertaService.trovaOfferteAsta(idAsta)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/asta/" + idAsta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("trovaOfferteAsta trova OfferteAsta")
    @Test
    void whenTrovaOfferteAsta_givenExistingOfferteAsta_thenReturnJsonArrayOfMapsOfferteAsta() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idUtente, "username", "email", "339025613",
                        "boh", 0, false, false);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idoff, 3.14f,
                        Timestamp.valueOf(LocalDateTime.now()), offerente));

        given(offertaService.trovaOfferteAsta(idasta)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/asta/" + idasta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                //.andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
    }

    // Test trovaOfferteUtente
    @DisplayName("trovaOfferteUtente non trova OfferteUtente")
    @Test
    void whenTrovaOfferteUtente_givenNonExistingOfferteUtente_thenReturnEmptyJsonArray() throws Exception {
        UUID idOfferente = UUID.randomUUID();

        List<OffertaModel> offerteTrovate = Collections.emptyList();

        given(offertaService.trovaOfferteUtente(idOfferente)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/offerente/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("trovaOfferteUtente trova OfferteUtente")
    @Test
    void whenTrovaOfferteUtente_givenExistingOfferteUtente_thenReturnJsonArrayOfMapsOfferteUtente() throws Exception {
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idOfferente, "username", "email", "339025613",
                        "boh", 0, false, false);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idOfferta, 3.14f,
                        Timestamp.valueOf(LocalDateTime.now()), offerente));

        given(offertaService.trovaOfferteUtente(idOfferente)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/offerente/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                //.andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
    }

    // Test trovaOfferteUtenteAsta
    @DisplayName("trovaOfferteUtenteAsta non trova OfferteUtenteAsta")
    @Test
    void whenTrovaOfferteUtenteAsta_givenNonExistingOfferteUtenteAsta_thenReturnEmptyJsonArray() throws Exception {
        UUID idOfferente = UUID.randomUUID();
        UUID idAsta = UUID.randomUUID();

        List<OffertaModel> offerteTrovate = Collections.emptyList();

        given(offertaService.trovaOfferteUtenteAsta(idOfferente, idAsta)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/" + idOfferente + "/" + idAsta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("trovaOfferteUtenteAsta trova OfferteUtenteAsta")
    @Test
    void whenTrovaOfferteUtenteAsta_givenExistingOfferteUtenteAsta_thenReturnJsonArrayOfMapsOfferteUtenteAsta() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idUtente, "username", "email", "339025613",
                        "boh", 0, false, false);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idoff, 3.14f,
                        Timestamp.valueOf(LocalDateTime.now()), offerente));

        given(offertaService.trovaOfferteUtenteAsta(idUtente, idasta)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/" + idUtente + "/" + idasta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                //.andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
    }


}