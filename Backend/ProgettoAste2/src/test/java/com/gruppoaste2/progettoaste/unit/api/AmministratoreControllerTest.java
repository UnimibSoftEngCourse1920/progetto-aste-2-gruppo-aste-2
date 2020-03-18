package com.gruppoaste2.progettoaste.unit.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruppoaste2.progettoaste.api.AmministratoreController;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.service.AmministratoreService;
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
@WebMvcTest(AmministratoreController.class)
class AmministratoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmministratoreService amministratoreService;

    // Test inserisciAmministratore
    @DisplayName("inserisciAmministratore non inserisce Amministratore")
    @Test
    void whenInserisciAmministratore_givenAlreadyExistingAmministratore_thenReturnEmptyJson() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.inserisciAmministratore(refEq(amministratore))).willReturn(null);

        mockMvc.perform(post("/api/amministratore/inserisci")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("inserisciAmministratore inserisce Amministratore")
    @Test
    void whenInserisciAmministratore_givenNonExistingAmministratore_thenReturnJsonStringIdAmministratore()
            throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        UUID idAmministratore = UUID.randomUUID();

        given(amministratoreService.inserisciAmministratore(refEq(amministratore))).willReturn(idAmministratore);

        mockMvc.perform(post("/api/amministratore/inserisci")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").value(idAmministratore.toString()));
    }

    // Test eliminaAmministratore
    @DisplayName("eliminaAmministratore non elimina Amministratore")
    @Test
    void whenEliminaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        given(amministratoreService.eliminaAmministratore(idAmministratore)).willReturn(0);

        mockMvc.perform(get("/api/amministratore/elimina/" + idAmministratore)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("eliminaAmministratore elimina Amministratore")
    @Test
    void whenEliminaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        given(amministratoreService.eliminaAmministratore(idAmministratore)).willReturn(1);

        mockMvc.perform(get("/api/amministratore/elimina/" + idAmministratore)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test trovaAmministratore
    @DisplayName("trovaAmministratore non trova Amministratore")
    @Test
    void whenTrovaAmministratore_givenNonExistingAmministratore_thenReturnEmptyJson() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato = Optional.empty();

        given(amministratoreService.trovaAmministratore(idAmministratore)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + idAmministratore)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("trovaAmministratore trova Amministratore")
    @Test
    void whenTrovaAmministratore_givenExistingAmministratore_thenReturnJsonMapAmministratore() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato =
                Optional.of(new AmministratoreModel(idAmministratore, "username", "email",
                        "password"));

        given(amministratoreService.trovaAmministratore(idAmministratore)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + idAmministratore)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(amministratoreTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(amministratoreTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(amministratoreTrovato.get().getEmail()))
                .andExpect(jsonPath("$.password").value(amministratoreTrovato.get().getPassword()));
    }

    // Test trovaAmministratori
    @DisplayName("trovaAmministratori non trova Amministratori")
    @Test
    void whenTrovaAmministratori_givenNonExistingAmministratori_thenReturnEmptyJsonArray() throws Exception {
        List<AmministratoreModel> amministratoriTrovati = Collections.emptyList();

        given(amministratoreService.trovaAmministratori()).willReturn(amministratoriTrovati);

        mockMvc.perform(get("/api/amministratore/amministratori")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("trovaAmministratori trova Amministratori")
    @Test
    void whenTrovaAmministratori_givenExistingAmministratori_thenReturnJsonArrayOfMapsAmministratori() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        List<AmministratoreModel> amministratoriTrovati =
                Collections.singletonList(new AmministratoreModel(idAmministratore, "username", "email",
                        "password"));

        given(amministratoreService.trovaAmministratori()).willReturn(amministratoriTrovati);

        mockMvc.perform(get("/api/amministratore/amministratori")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(amministratoriTrovati.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].username").value(amministratoriTrovati.get(0).getUsername()))
                .andExpect(jsonPath("$[0].email").value(amministratoriTrovati.get(0).getEmail()))
                .andExpect(jsonPath("$[0].password").value(amministratoriTrovati.get(0).getPassword()));
    }

    // Test aggiornaAmministratore
    @DisplayName("aggiornaAmministratore non aggiorna Amministratore")
    @Test
    void whenAggiornaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        AmministratoreModel amministratore =
                new AmministratoreModel(idAmministratore, "username", "email", "password");

        given(amministratoreService.aggiornaAmministratore(refEq(idAmministratore), refEq(amministratore)))
                .willReturn(0);

        mockMvc.perform(post("/api/amministratore/aggiorna/" + idAmministratore)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("aggiornaAmministratore aggiorna Amministratore")
    @Test
    void whenAggiornaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        AmministratoreModel amministratore =
                new AmministratoreModel(idAmministratore, "username", "email", "password");

        given(amministratoreService.aggiornaAmministratore(refEq(idAmministratore), refEq(amministratore)))
                .willReturn(1);

        mockMvc.perform(post("/api/amministratore/aggiorna/" + idAmministratore)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test controllaUsernameOccupato
    @DisplayName("controllaUsernameOccupato non trova username non occupato")
    @Test
    void whenControllaUsernameOccupato_givenUsernameNonOccupato_thenReturnJsonBooleanFalse() throws Exception {
        String username = "username";

        given(amministratoreService.controllaUsernameOccupato(username)).willReturn(false);

        mockMvc.perform(get("/api/amministratore/controlla/username/" + username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @DisplayName("controllaUsernameOccupato trova username occupato")
    @Test
    void whenControllaUsernameOccupato_givenUsernameOccupato_thenReturnJsonBooleanTrue() throws Exception {
        String username = "username";

        given(amministratoreService.controllaUsernameOccupato(username)).willReturn(true);

        mockMvc.perform(get("/api/amministratore/controlla/username/" + username)
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

        given(amministratoreService.controllaEmailOccupata(email)).willReturn(false);

        mockMvc.perform(get("/api/amministratore/controlla/email/" + email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @DisplayName("controllaEmailOccupata trova email occupata")
    @Test
    void whenControllaEmailOccupata_givenEmailOccupata_thenReturnJsonBooleanTrue() throws Exception {
        String email = "email";

        given(amministratoreService.controllaEmailOccupata(email)).willReturn(true);

        mockMvc.perform(get("/api/amministratore/controlla/email/" + email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test controllaAmministratoreEsiste
    @DisplayName("controllaAmministratoreEsiste non trova Amministratore")
    @Test
    void whenControllaAmministratoreEsiste_givenNonExistingAmministratore_thenReturnJsonBooleanFalse() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.controllaAmministratoreEsiste(refEq(amministratore))).willReturn(false);

        mockMvc.perform(post("/api/amministratore/controlla/amministratore")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @DisplayName("controllaAmministratoreEsiste trova Amministratore")
    @Test
    void whenControllaAmministratoreEsiste_givenExistingAmministratore_thenReturnJsonBooleanTrue() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.controllaAmministratoreEsiste(refEq(amministratore))).willReturn(true);

        mockMvc.perform(post("/api/amministratore/controlla/amministratore")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test ritornaIdAmministratore
    @DisplayName("ritornaIdAmministratore non trova Amministratore")
    @Test
    void whenRitornaIdAmministratore_givenNonExistingAmministratore_thenReturnEmptyJson() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.ritornaIdAmministratore(refEq(amministratore))).willReturn(null);

        mockMvc.perform(post("/api/amministratore/id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("ritornaIdAmministratore trova Amministratore")
    @Test
    void whenRitornaIdAmministratore_givenExistingAmministratore_thenReturnIdAmministratore() throws Exception {
        UUID idAmministratore = UUID.randomUUID();

        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.ritornaIdAmministratore(refEq(amministratore))).willReturn(idAmministratore);

        mockMvc.perform(post("/api/amministratore/id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").value(idAmministratore.toString()));
    }
}