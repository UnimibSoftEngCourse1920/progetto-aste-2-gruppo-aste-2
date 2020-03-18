package com.gruppoaste2.progettoaste.unit.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruppoaste2.progettoaste.api.UtenteRegistratoController;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.UtenteRegistratoService;
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
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UtenteRegistratoController.class)
class UtenteRegistratoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtenteRegistratoService utenteRegistratoService;

    // Test aggiungiUtenteRegistrato
    @DisplayName("aggiungiUtenteRegistrato non aggiunge UtenteRegistrato")
    @Test
    void whenAggiungiUtenteRegistrato_givenAlreadyExistingUtenteRegistrato_thenReturnEmptyJson() throws Exception {
        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(null, "username", "email", "password",
                        "0", 0.0f, false, false);

        given(utenteRegistratoService.aggiungiUtenteRegistrato(refEq(utenteRegistrato))).willReturn(null);

        mockMvc.perform(post("/api/utenteregistrato/aggiungi")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("aggiungiUtenteRegistrato aggiunge UtenteRegistrato")
    @Test
    void whenAggiungiUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnJsonStringIdUtenteRegistrato()
            throws Exception {
        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(null, "username", "email", "password",
                        "0", 0.0f, false, false);

        UUID idUtenteRegistrato = UUID.randomUUID();

        given(utenteRegistratoService.aggiungiUtenteRegistrato(refEq(utenteRegistrato))).willReturn(idUtenteRegistrato);

        mockMvc.perform(post("/api/utenteregistrato/aggiungi")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").value(idUtenteRegistrato.toString()));
    }

    // Test eliminaUtenteRegistrato
    @DisplayName("eliminaUtenteRegistrato non elimina UtenteRegistrato")
    @Test
    void whenEliminaUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnJsonNumber0() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        given(utenteRegistratoService.eliminaUtenteRegistrato(idUtenteRegistrato)).willReturn(0);

        mockMvc.perform(get("/api/utenteregistrato/elimina/" + idUtenteRegistrato)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("eliminaUtenteRegistrato elimina UtenteRegistrato")
    @Test
    void whenEliminaUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnJsonNumber1() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        given(utenteRegistratoService.eliminaUtenteRegistrato(idUtenteRegistrato)).willReturn(1);

        mockMvc.perform(get("/api/utenteregistrato/elimina/" + idUtenteRegistrato)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test trovaUtenteRegistrato
    @DisplayName("trovaUtenteRegistrato non trova UtenteRegistrato")
    @Test
    void whenTrovaUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnEmptyJson() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        Optional<UtenteRegistratoModel> utenteRegistratoTrovato = Optional.empty();

        given(utenteRegistratoService.trovaUtenteRegistrato(idUtenteRegistrato)).willReturn(utenteRegistratoTrovato);

        mockMvc.perform(get("/api/utenteregistrato/" + idUtenteRegistrato)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("trovaUtenteRegistrato trova UtenteRegistrato")
    @Test
    void whenUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnJsonMapUtenteRegistrato() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        Optional<UtenteRegistratoModel> utenteRegistratoTrovato =
                Optional.of(new UtenteRegistratoModel(idUtenteRegistrato, "username", "email",
                        "numeroTelefono", "password", 3.14f, false,
                        false));

        given(utenteRegistratoService.trovaUtenteRegistrato(idUtenteRegistrato)).willReturn(utenteRegistratoTrovato);

        mockMvc.perform(get("/api/utenteregistrato/" + idUtenteRegistrato)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(utenteRegistratoTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(utenteRegistratoTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(utenteRegistratoTrovato.get().getEmail()))
                .andExpect(jsonPath("$.numeroTelefono").value(utenteRegistratoTrovato.get()
                        .getNumeroTelefono()))
                .andExpect(jsonPath("$.password").value(utenteRegistratoTrovato.get().getPassword()))
                .andExpect(jsonPath("$.credito").value(utenteRegistratoTrovato.get().getCredito()));
    }

    // Test trovaUtentiRegistrati
    @DisplayName("trovaUtentiRegistrati non trova UtentiRegistrati")
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

    @DisplayName("trovaUtentiRegistrati trova UtentiRegistrati")
    @Test
    void whenTrovaUtentiRegistrati_givenExistingUtentiRegistrati_thenReturnJsonArrayOfMapsUtentiRegistrati() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        List<UtenteRegistratoModel> utentiRegistratiTrovati =
                Collections.singletonList(new UtenteRegistratoModel(idUtenteRegistrato,"username","email",
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
                .andExpect(jsonPath("$[0].numeroTelefono").value(utentiRegistratiTrovati.get(0)
                        .getNumeroTelefono()))
                .andExpect(jsonPath("$[0].password").value(utentiRegistratiTrovati.get(0).getPassword()))
                .andExpect(jsonPath("$[0].credito").value(utentiRegistratiTrovati.get(0).getCredito()));
    }

    // Test aggiornaUtenteRegistrato
    @DisplayName("aggiornaUtenteRegistrato non aggiorna UtenteRegistrato")
    @Test
    void whenAggiornaUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnJsonNumber0() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(idUtenteRegistrato, "username", "email", "password", "3336669991", 0.0f, true, true);

        given(utenteRegistratoService.aggiornaUtenteRegistrato(refEq(idUtenteRegistrato), refEq(utenteRegistrato)))
                .willReturn(0);

        mockMvc.perform(post("/api/utenteregistrato/aggiorna/" + idUtenteRegistrato)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("aggiornaUtenteRegistrato aggiorna UtenteRegistrato")
    @Test
    void whenAggiornaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(idUtenteRegistrato, "username", "email", "password", "3336669991", 0.0f, true, true);

        given(utenteRegistratoService.aggiornaUtenteRegistrato(refEq(idUtenteRegistrato), refEq(utenteRegistrato)))
                .willReturn(1);

        mockMvc.perform(post("/api/utenteregistrato/aggiorna/" + idUtenteRegistrato)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test controllaUsernameOccupato
    @DisplayName("controllaUsernameOccupato non trova username non occupato")
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

    @DisplayName("controllaUsernameOccupato trova username non occupato")
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
    @DisplayName("controllaEmailOccupata non trova email non occupata")
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

    @DisplayName("controllaEmailOccupata trova email non occupata")
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

    // Test controllaUtenteRegistratoEsiste
    @DisplayName("controllaUtenteRegistratoEsiste non trova UtenteRegistrato")
    @Test
    void whenControllaUtenteRegistratoEsiste_givenNonExistingUtenteRegistrato_thenReturnJsonBooleanFalse() throws Exception {
        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(null, "username", "email", "password",
                        "0", 0.0f, false, false);

        given(utenteRegistratoService.controllaUtenteEsiste(refEq(utenteRegistrato))).willReturn(false);

        mockMvc.perform(post("/api/utenteregistrato/controlla/utente")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @DisplayName("controllaUtenteRegistratoEsiste trova UtenteRegistrato")
    @Test
    void whenControllaUtenteRegistratoEsiste_givenExistingUtenteRegistrato_thenReturnJsonBooleanTrue() throws Exception {
        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(null, "username", "email", "password",
                        "0", 0.0f, false, false);

        given(utenteRegistratoService.controllaUtenteEsiste(refEq(utenteRegistrato))).willReturn(true);

        mockMvc.perform(post("/api/utenteregistrato/controlla/utente")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test ritornaIdUtenteRegistrato
    @DisplayName("ritornaIdUtenteRegistrato non trova UtenteRegistrato")
    @Test
    void whenRitornaIdUtenteRegistrato_givenNonExistingUtenteRegistrato_thenReturnEmptyJson() throws Exception {
        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(null, "username", "email", "password",
                        "0", 0.0f, false, false);

        given(utenteRegistratoService.ritornaIdUtenteRegistrato(refEq(utenteRegistrato))).willReturn(null);

        mockMvc.perform(post("/api/utenteregistrato/id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("ritornaIdUtenteRegistrato trova UtenteRegistrato")
    @Test
    void whenRitornaIdUtenteRegistrato_givenExistingUtenteRegistrato_thenReturnIdUtenteRegistrato() throws Exception {
        UUID idUtenteRegistrato = UUID.randomUUID();

        UtenteRegistratoModel utenteRegistrato =
                new UtenteRegistratoModel(null, "username", "email", "password",
                        "0", 0.0f, false, false);

        given(utenteRegistratoService.ritornaIdUtenteRegistrato(refEq(utenteRegistrato))).willReturn(idUtenteRegistrato);

        mockMvc.perform(post("/api/utenteregistrato/id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(utenteRegistrato)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").value(idUtenteRegistrato.toString()));
    }
}