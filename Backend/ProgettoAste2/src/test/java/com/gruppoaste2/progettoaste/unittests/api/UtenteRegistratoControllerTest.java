package com.gruppoaste2.progettoaste.unittests.api;

import com.gruppoaste2.progettoaste.api.UtenteRegistratoController;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.UtenteRegistratoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UtenteRegistratoController.class)
class UtenteRegistratoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtenteRegistratoService utenteRegistratoService;

    // Test trovaUtenteRegistrato
    @Test
    void whenTrovaUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<UtenteRegistratoModel> utenteRegistratoTrovato = Optional.empty();

        given(utenteRegistratoService.trovaUtenteRegistrato(id)).willReturn(utenteRegistratoTrovato);

        mockMvc.perform(get("/api/utenteregistrato/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void whenUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnJsonMapUtenteRegistrato() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<UtenteRegistratoModel> utenteRegistratoTrovato =
                Optional.of(new UtenteRegistratoModel(id, "username", "email",
                        "numeroTelefono", "password", 3.14f, false,
                        false));

        given(utenteRegistratoService.trovaUtenteRegistrato(id)).willReturn(utenteRegistratoTrovato);

        mockMvc.perform(get("/api/utenteregistrato/" + id)
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

    // Test trovaUtentiRegistrati
    @Test
    void whenTrovaUtentiRegistrati_givenNonExistingUtentiRegistrati_thenReturnEmptyJsonArray() throws Exception {
        List<UtenteRegistratoModel> utentiRegistratiTrovati = Collections.emptyList();

        given(utenteRegistratoService.trovaUtentiRegistrati()).willReturn(utentiRegistratiTrovati);

        mockMvc.perform(get("/api/utenteregistrato/utenti")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void whenTrovaUtentiRegistrati_givenExistingUtentiRegistrati_thenReturnJsonArrayOfMapsUtentiRegistrati() throws Exception {
        UUID id = UUID.randomUUID();

        List<UtenteRegistratoModel> utentiRegistratiTrovati =
                Collections.singletonList(new UtenteRegistratoModel(id,"username","email",
                        "numeroTelefono", "password", 3.14f, false,
                        false));

        given(utenteRegistratoService.trovaUtentiRegistrati()).willReturn(utentiRegistratiTrovati);

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

    // Test aggiungiUtenteRegistrato
    @Test
    void whenAggiungiUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenAggiungiUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnJsonNumber1() throws Exception {

    }

    // Test aggiornaUtenteRegistrato
    @Test
    void whenAggiornaUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenAggiornaUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnJsonNumber1() throws Exception {

    }

    // Test eliminaUtenteRegistrato
    @Test
    void whenEliminaUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(utenteRegistratoService.eliminaUtenteRegistrato(id)).willReturn(0);

        mockMvc.perform(get("/api/utenteregistrato/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    void whenEliminaUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(utenteRegistratoService.eliminaUtenteRegistrato(id)).willReturn(1);

        mockMvc.perform(get("/api/utenteregistrato/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test controllaUsernameOccupato
    @Test
    void whenControllaUsernameOccupato_givenUsernameNonOccupato_thenReturnJsonBooleanFalse() throws Exception {
        String username = "username";

        given(utenteRegistratoService.controllaUsernameOccupato(username)).willReturn(false);

        mockMvc.perform(get("/api/utenteregistrato/controlla/username/" + username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    void whenControllaUsernameOccupato_givenUsernameOccupato_thenReturnJsonBooleanTrue() throws Exception {
        String username = "username";

        given(utenteRegistratoService.controllaUsernameOccupato(username)).willReturn(true);

        mockMvc.perform(get("/api/utenteregistrato/controlla/username/" + username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test controllaEmailOccupata
    @Test
    void whenControllaEmailOccupata_givenEmailNonOccupata_thenReturnJsonBooleanFalse() throws Exception {
        String email = "email";

        given(utenteRegistratoService.controllaEmailOccupata(email)).willReturn(false);

        mockMvc.perform(get("/api/utenteregistrato/controlla/email/" + email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    void whenControllaEmailOccupata_givenEmailOccupata_thenReturnJsonBooleanTrue() throws Exception {
        String email = "email";

        given(utenteRegistratoService.controllaEmailOccupata(email)).willReturn(true);

        mockMvc.perform(get("/api/utenteregistrato/controlla/email/" + email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test controllaUtenteEsiste
    @Test
    void whenControllaUtenteRegistratoEsiste_givenNonExistingUtenteRegistrato_thenReturnJsonBooleanFalse() throws Exception {

    }

    @Test
    void whenControllaUtenteRegistratoEsiste_givenExistingUtenteRegistrato_thenReturnJsonBooleanTrue() throws Exception {

    }

    //test infoCredito
    @Test
    void whenInfoCredito_givenNonExistingCredito_thenReturnEmptyJson() throws Exception {

    }

    @Test
    void whenInfoCredito_givenExistingCredito_thenReturnJsonMapInfoCredito() throws Exception {

    }

    //test aggiungiCredito
    @Test
    void whenAggiungiCredito_givenNonExistingCredito_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenAggiungiCredito_givenExistingCredito_thenReturnJsonNumber1() throws Exception {

    }
}