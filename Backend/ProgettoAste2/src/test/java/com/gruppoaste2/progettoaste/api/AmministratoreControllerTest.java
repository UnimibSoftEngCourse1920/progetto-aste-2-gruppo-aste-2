package com.gruppoaste2.progettoaste.api;

import com.google.gson.Gson;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.service.AmministratoreService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AmministratoreController.class)
public class AmministratoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmministratoreService amministratoreService;

    // Test inserisciAmministratore
    @Test
    public void whenInserisciAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        /*
        String body = "{"
                + "\"username\":\"username\","
                + "\"email\":\"email\","
                + "\"password\":\"password\""
                + "}";

        given(amministratoreService.inserisciAmministratore(new Gson().fromJson(body, AmministratoreModel.class))).willReturn(1);

        mockMvc.perform(post("/api/amministratore/inserisci")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
         */
    }

    @Test
    public void whenInserisciAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        /*
        String body = "{"
                + "\"username\":\"username\","
                + "\"email\":\"email\","
                + "\"password\":\"password\""
                + "}";

        given(amministratoreService.inserisciAmministratore(new Gson().fromJson(body, AmministratoreModel.class))).willReturn(0);

        mockMvc.perform(post("/api/amministratore/inserisci")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
         */
    }

    // Test trovaAmministratore
    @Test
    public void whenTrovaAmministratore_givenNonExistingAmministratore_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato = Optional.empty();

        given(amministratoreService.trovaAmministratore(id)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenTrovaAmministratore_givenExistingAmministratore_thenReturnJsonMapAmministratore() throws Exception {
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
    public void whenTrovaAmministratori_givenNonExistingAmministratori_thenReturnEmptyJsonArray() throws Exception {
        List<AmministratoreModel> amministratoriTrovati = Collections.emptyList();

        given(amministratoreService.trovaAmministratori()).willReturn(amministratoriTrovati);

        mockMvc.perform(get("/api/amministratore/amministratori")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaAmministratori_givenExistingAmministratori_thenReturnJsonArrayOfMapsAmministratori() throws Exception {
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

    // Test controllaUsernameOccupato
    @Test
    public void whenControllaUsernameOccupato_givenUsernameNonOccupato_thenReturnJsonBooleanFalse() throws Exception {
        String username = "username";

        given(amministratoreService.controllaUsernameOccupato(username)).willReturn(false);

        mockMvc.perform(get("/api/amministratore/controlla/username/" + username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    public void whenControllaUsernameOccupato_givenUsernameOccupato_thenReturnJsonBooleanTrue() throws Exception {
        String username = "username";

        given(amministratoreService.controllaUsernameOccupato(username)).willReturn(true);

        mockMvc.perform(get("/api/amministratore/controlla/username/" + username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test eliminaAmministratore
    @Test
    public void whenEliminaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(amministratoreService.eliminaAmministratore(id)).willReturn(0);

        mockMvc.perform(get("/api/amministratore/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    public void whenEliminaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(amministratoreService.eliminaAmministratore(id)).willReturn(1);

        mockMvc.perform(get("/api/amministratore/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test controllaEmailOccupata
    @Test
    public void whenControllaEmailOccupata_givenEmailNonOccupata_thenReturnJsonBooleanFalse() throws Exception {
        String email = "email";

        given(amministratoreService.controllaEmailOccupata(email)).willReturn(false);

        mockMvc.perform(get("/api/amministratore/controlla/email/" + email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    public void whenControllaEmailOccupata_givenEmailOccupata_thenReturnJsonBooleanTrue() throws Exception {
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
    public void whenControllaAmministratoreEsiste_givenNonExistingAmministratore_thenReturnJsonBooleanFalse() throws Exception {
        String username = "username";
        String email = "email";
        String password = "password";

        AmministratoreModel amministratore = new AmministratoreModel(null, username, email, password);

        given(amministratoreService.controllaAmministratoreEsiste(amministratore)).willReturn(false);

        String body = "{"
                + "\"username\":\"" + username + "\","
                + "\"email\":\"" + email + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        mockMvc.perform(get("/api/amministratore/controlla/utente")
                .characterEncoding("UTF-8")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    public void whenControllaAmministratoreEsiste_givenExistingAmministratore_thenReturnJsonBooleanTrue() throws Exception {
        String username = "username";
        String email = "email";
        String password = "password";

        AmministratoreModel amministratore = new AmministratoreModel(null, username, email, password);

        given(amministratoreService.controllaAmministratoreEsiste(amministratore)).willReturn(true);

        String body = "{"
                + "\"username\":\"" + username + "\","
                + "\"email\":\"" + email + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        mockMvc.perform(get("/api/amministratore/controlla/utente")
                .characterEncoding("UTF-8")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isBoolean())
                .andExpect(jsonPath("$").value(true));
    }

    // Test aggiornaAmministratore
    @Test
    public void whenAggiornaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {
        /*
        UUID id = UUID.randomUUID();

        AmministratoreModel amministratore = new AmministratoreModel(id,"username","email","password");

        given(amministratoreService.aggiornaAmministratore(id, amministratore)).willReturn(0);

        mockMvc.perform(post("/api/amministratore/aggiorna/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
         */
    }

    @Test
    public void whenAggiornaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {
        /*
        UUID id = UUID.randomUUID();

        AmministratoreModel amministratore = new AmministratoreModel(id,"username","email","password");

        given(amministratoreService.aggiornaAmministratore(id, amministratore)).willReturn(1);

        mockMvc.perform(post("/api/amministratore/aggiorna/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
         */
    }
}