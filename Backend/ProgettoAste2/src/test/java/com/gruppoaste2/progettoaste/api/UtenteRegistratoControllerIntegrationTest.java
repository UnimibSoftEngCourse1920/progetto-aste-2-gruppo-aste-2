package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.AmministratoreService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UtenteRegistratoController.class)
class UtenteRegistratoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtenteRegistratoService utenteRegistratoService;

    @Test
    void esempioGet() {
    }

    @Test
    public void whenTrovaUtenteRegistrato_givenNotExistingUtenteRegistrato_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<UtenteRegistratoModel> utenteRegistratoTrovato = Optional.ofNullable(null);

        given(utenteRegistratoService.trovaUtenteRegistro(id)).willReturn(utenteRegistratoTrovato);

        mockMvc.perform(get("/api/utenteregistrato/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnJsonUtenteRegistrato() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<UtenteRegistratoModel> utenteRegistratoTrovato =
                Optional.ofNullable(new UtenteRegistratoModel(id,"username","email", "numeroTelefono", "password", 3.14f));

        given(utenteRegistratoService.trovaUtenteRegistro(id)).willReturn(utenteRegistratoTrovato);

        mockMvc.perform(get("/api/utenteregistrato/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(utenteRegistratoTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(utenteRegistratoTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(utenteRegistratoTrovato.get().getEmail()))
                .andExpect(jsonPath("$.numeroTelefono").value(utenteRegistratoTrovato.get().getNumeroTelefono()))
                .andExpect(jsonPath("$.password").value(utenteRegistratoTrovato.get().getPassword()))
                .andExpect(jsonPath("$.credito").value(utenteRegistratoTrovato.get().getCredito()));
    }

    @Test
    public void whenTrovaUtentiRegistrati_givenNotExistingUtentiRegistrati_thenReturnEmptyJson() throws Exception {
        List<UtenteRegistratoModel> utentiRegistratiTrovati = Arrays.asList();

        given(utenteRegistratoService.trovaTuttiUtentiRegistrati()).willReturn(utentiRegistratiTrovati);

        mockMvc.perform(get("/api/utenteregistrato/utenti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaUtentiRegistrati_givenExistingUtentiRegistrati_thenReturnJsonArray() throws Exception {
        UUID id = UUID.randomUUID();

        List<UtenteRegistratoModel> utentiRegistratiTrovati =
                Arrays.asList(new UtenteRegistratoModel(id,"username","email", "numeroTelefono", "password", 3.14f));

        given(utenteRegistratoService.trovaTuttiUtentiRegistrati()).willReturn(utentiRegistratiTrovati);

        mockMvc.perform(get("/api/utenteregistrato/utenti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(utentiRegistratiTrovati.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].username").value(utentiRegistratiTrovati.get(0).getUsername()))
                .andExpect(jsonPath("$[0].email").value(utentiRegistratiTrovati.get(0).getEmail()))
                .andExpect(jsonPath("$[0].numeroTelefono").value(utentiRegistratiTrovati.get(0).getNumeroTelefono()))
                .andExpect(jsonPath("$[0].password").value(utentiRegistratiTrovati.get(0).getPassword()))
                .andExpect(jsonPath("$[0].credito").value(utentiRegistratiTrovati.get(0).getCredito()));
    }

    @Test
    void aggiungiUtenteRegistrato() {
    }

    @Test
    void aggiornaUtenteRegistrato() {
    }

    @Test
    void eliminaUtenteRegistrato() {
    }

    @Test
    void controllaUsernameOccupato() {
    }

    @Test
    void controllaEmailOccupata() {
    }

    @Test
    void controllaUtenteEsiste() {
    }

    @Test
    void infoCredito() {
    }

    @Test
    void aggiungiCredito() {
    }
}