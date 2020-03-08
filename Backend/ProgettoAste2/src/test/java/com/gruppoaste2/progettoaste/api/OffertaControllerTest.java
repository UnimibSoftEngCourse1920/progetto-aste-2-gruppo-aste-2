package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.*;
import com.gruppoaste2.progettoaste.service.OffertaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OffertaController.class)
public class OffertaControllerTest {

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

    // Test trovaOfferte
    @Test
    public void whenTrovaOfferte_givenNonExistingOfferte_thenReturnEmptyJsonArray() throws Exception {
        List<OffertaModel> offerteTrovate = Collections.emptyList();

        given(offertaService.trovaOfferte()).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaOfferte_givenExistingOfferte_thenReturnJsonArrayOfMapsOfferte() throws Exception {
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idOfferente, "username", "email", "numeroTelefono", "password", 10.5f);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idOfferta, 3.14f, Date.valueOf(LocalDate.now()), offerente));

        given(offertaService.trovaOfferte()).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                .andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
    }

    // Test trovaOfferteAsta
    @Test
    public void whenTrovaOfferteAsta_givenNonExistingOfferteAsta_thenReturnEmptyJsonArray() throws Exception {
        UUID idAsta = UUID.randomUUID();

        List<OffertaModel> offerteTrovate = Collections.emptyList();

        given(offertaService.trovaOfferteAsta(idAsta)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/asta/" + idAsta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaOfferteAsta_givenExistingOfferteAsta_thenReturnJsonArrayOfMapsOfferteAsta() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url");
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Date.valueOf(LocalDate.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613", "boh1", 2));
        offerte.add(off1);

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idUtente, "username", "email", "339025613", "boh", 0);

        AstaModel astaTrovata = new AstaModel (idasta,
                new InfoAstaModel("info", 3.4,
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now()),
                        Time.valueOf(LocalTime.now())),
                new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                        Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now())),
                oggetti, offerente, offerte);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idoff, 3.14f, Date.valueOf(LocalDate.now()), offerente));

        given(offertaService.trovaOfferteAsta(idasta)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/asta/" + idasta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                .andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
    }

    // Test trovaOfferteUtente
    @Test
    public void whenTrovaOfferteUtente_givenNonExistingOfferteUtente_thenReturnEmptyJsonArray() throws Exception {
        UUID idOfferente = UUID.randomUUID();

        List<OffertaModel> offerteTrovate = Collections.emptyList();

        given(offertaService.trovaOfferteUtente(idOfferente)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/offerente/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaOfferteUtente_givenExistingOfferteUtente_thenReturnJsonArrayOfMapsOfferteUtente() throws Exception {
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idOfferente, "username", "email", "339025613", "boh", 0);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idOfferta, 3.14f, Date.valueOf(LocalDate.now()), offerente));

        given(offertaService.trovaOfferteUtente(idOfferente)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/offerente/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                .andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
    }

    // Test trovaOfferteUtenteAsta
    @Test
    public void whenTrovaOfferteUtenteAsta_givenNonExistingOfferteUtenteAsta_thenReturnEmptyJsonArray() throws Exception {
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

    @Test
    public void whenTrovaOfferteUtenteAsta_givenExistingOfferteUtenteAsta_thenReturnJsonArrayOfMapsOfferteUtenteAsta() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url");
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Date.valueOf(LocalDate.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613", "boh1", 2));
        offerte.add(off1);

        UtenteRegistratoModel offerente =
                new UtenteRegistratoModel(idUtente, "username", "email", "339025613", "boh", 0);

        AstaModel astaTrovata = new AstaModel (idasta,
                new InfoAstaModel("info", 3.4,
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now()),
                        Time.valueOf(LocalTime.now())),
                new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                        Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now())),
                oggetti, offerente, offerte);

        List<OffertaModel> offerteTrovate =
                Collections.singletonList(new OffertaModel(idoff, 3.14f, Date.valueOf(LocalDate.now()), offerente));

        given(offertaService.trovaOfferteUtenteAsta(idUtente, idasta)).willReturn(offerteTrovate);

        mockMvc.perform(get("/api/offerta/offerte/" + idUtente + "/" + idasta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(offerteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].creditoOfferto").value(offerteTrovate.get(0).getCreditoOfferto()))
                .andExpect(jsonPath("$[0].dataOfferta").value(offerteTrovate.get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerente.id").value(offerteTrovate.get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerente.username").value(offerteTrovate.get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerente.email").value(offerteTrovate.get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerente.numeroTelefono").value(offerteTrovate.get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerente.password").value(offerteTrovate.get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerente.credito").value(offerteTrovate.get(0).getOfferente().getCredito()));
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
    public void whenEliminaOfferta_givenNonExistingOfferta_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(offertaService.eliminaOfferta(id)).willReturn(0);

        mockMvc.perform(get("/api/offerta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    public void whenEliminaOfferta_givenExistingOfferta_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(offertaService.eliminaOfferta(id)).willReturn(1);

        mockMvc.perform(get("/api/offerta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }
}