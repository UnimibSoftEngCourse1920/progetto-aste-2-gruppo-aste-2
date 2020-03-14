package com.gruppoaste2.progettoaste.unittests.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruppoaste2.progettoaste.api.AmministratoreController;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.service.AmministratoreService;
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
    @Test
    void whenInserisciAmministratore_givenAlreadyExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.inserisciAmministratore(refEq(amministratore))).willReturn(0);

        mockMvc.perform(post("/api/amministratore/inserisci")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    void whenInserisciAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.inserisciAmministratore(refEq(amministratore))).willReturn(1);

        mockMvc.perform(post("/api/amministratore/inserisci")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test eliminaAmministratore
    @Test
    void whenEliminaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(amministratoreService.eliminaAmministratore(id)).willReturn(0);

        mockMvc.perform(get("/api/amministratore/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    void whenEliminaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(amministratoreService.eliminaAmministratore(id)).willReturn(1);

        mockMvc.perform(get("/api/amministratore/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test trovaAmministratore
    @Test
    void whenTrovaAmministratore_givenNonExistingAmministratore_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato = Optional.empty();

        given(amministratoreService.trovaAmministratore(id)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void whenTrovaAmministratore_givenExistingAmministratore_thenReturnJsonMapAmministratore() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato =
                Optional.of(new AmministratoreModel(id,"username","email","password"));

        given(amministratoreService.trovaAmministratore(id)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(amministratoreTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(amministratoreTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(amministratoreTrovato.get().getEmail()))
                .andExpect(jsonPath("$.password").value(amministratoreTrovato.get().getPassword()));
    }

    // Test trovaAmministratori
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

    @Test
    void whenTrovaAmministratori_givenExistingAmministratori_thenReturnJsonArrayOfMapsAmministratori() throws Exception {
        UUID id = UUID.randomUUID();

        List<AmministratoreModel> amministratoriTrovati =
                Collections.singletonList(new AmministratoreModel(id,"username","email","password"));

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
    @Test
    void whenAggiornaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        AmministratoreModel amministratore =
                new AmministratoreModel(id, "username", "email", "password");

        given(amministratoreService.aggiornaAmministratore(refEq(id), refEq(amministratore))).willReturn(0);

        mockMvc.perform(post("/api/amministratore/aggiorna/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    void whenAggiornaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        AmministratoreModel amministratore =
                new AmministratoreModel(id, "username", "email", "password");

        given(amministratoreService.aggiornaAmministratore(refEq(id), refEq(amministratore))).willReturn(1);

        mockMvc.perform(post("/api/amministratore/aggiorna/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test controllaUsernameOccupato
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
    @Test
    void whenControllaAmministratoreEsiste_givenNonExistingAmministratore_thenReturnJsonBooleanFalse() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.controllaAmministratoreEsiste(refEq(amministratore))).willReturn(false);

        mockMvc.perform(post("/api/amministratore/controlla/amministratore")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    void whenControllaAmministratoreEsiste_givenExistingAmministratore_thenReturnJsonBooleanTrue() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.controllaAmministratoreEsiste(refEq(amministratore))).willReturn(true);

        mockMvc.perform(post("/api/amministratore/controlla/amministratore")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test ritornaIdAmministratore
    @Test
    void whenRitornaIdAmministratore_givenNonExistingAmministratore_thenReturnEmptyJson() throws Exception {
        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.ritornaIdAmministratore(refEq(amministratore))).willReturn(null);

        mockMvc.perform(post("/api/amministratore/id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void whenRitornaIdAmministratore_givenExistingAmministratore_thenReturnIdAmministratore() throws Exception {
        UUID id = UUID.randomUUID();

        AmministratoreModel amministratore =
                new AmministratoreModel(null, "username", "email", "password");

        given(amministratoreService.ritornaIdAmministratore(refEq(amministratore))).willReturn(id);

        mockMvc.perform(post("/api/amministratore/id")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(amministratore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").value(id.toString()));
    }
}