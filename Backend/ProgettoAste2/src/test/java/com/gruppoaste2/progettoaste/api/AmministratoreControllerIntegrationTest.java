package com.gruppoaste2.progettoaste.api;

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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AmministratoreController.class)
class AmministratoreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmministratoreService amministratoreService;

    @Test
    public void inserisciAmministratoreShouldReturn0() throws Exception {

    }

    @Test
    public void inserisciAmministratoreShouldReturn1() throws Exception {

    }

    @Test
    public void trovaAmministratore_emptyDatabase_returnOptionalEmpty() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato =
                Optional.of(new AmministratoreModel(id,"username","email","password"));

        given(amministratoreService.trovaAmministratore(id)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(amministratoreTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(amministratoreTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(amministratoreTrovato.get().getEmail()))
                .andExpect(jsonPath("$.password").value(amministratoreTrovato.get().getPassword()));
    }

    @Test
    public void trovaAmministratore_existingAmministratore_returnAmministratore() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato =
                Optional.of(new AmministratoreModel(id,"username","email","password"));

        given(amministratoreService.trovaAmministratore(id)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(amministratoreTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(amministratoreTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(amministratoreTrovato.get().getEmail()))
                .andExpect(jsonPath("$.password").value(amministratoreTrovato.get().getPassword()));
    }

    @Test
    public void trovaAmministratoriTest() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Optional<List<AmministratoreModel>> amministratoriTrovati = Optional.of(new ArrayList<AmministratoreModel>());

        amministratoriTrovati.get().add(new AmministratoreModel(id1,"username1","email1","password1"));
        amministratoriTrovati.get().add(new AmministratoreModel(id2,"username2","email2","password2"));

        given(amministratoreService.trovaAmministratori()).willReturn(amministratoriTrovati);

        mockMvc.perform(get("/api/amministratore/amministratori")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(amministratoriTrovati.get().get(0).getId().toString()))
                .andExpect(jsonPath("$[0].username").value(amministratoriTrovati.get().get(0).getUsername()))
                .andExpect(jsonPath("$[0].email").value(amministratoriTrovati.get().get(0).getEmail()))
                .andExpect(jsonPath("$[0].password").value(amministratoriTrovati.get().get(0).getPassword()))
                .andExpect(jsonPath("$[1].id").value(amministratoriTrovati.get().get(1).getId().toString()))
                .andExpect(jsonPath("$[1].username").value(amministratoriTrovati.get().get(1).getUsername()))
                .andExpect(jsonPath("$[1].email").value(amministratoriTrovati.get().get(1).getEmail()))
                .andExpect(jsonPath("$[1].password").value(amministratoriTrovati.get().get(1).getPassword()));
    }

    @Test
    public void controllaUsernameOccupatoTest() throws Exception {
        String username = "username";

        given(amministratoreService.controllaUsernameOccupato(username)).willReturn(true);

        mockMvc.perform(get("/api/amministratore/controlla/username/" + username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminaAmministratoreTest() throws Exception {
        UUID id = UUID.randomUUID();

        given(amministratoreService.eliminaAmministratore(id)).willReturn(0);

        mockMvc.perform(get("/api/amministratore/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void controllaEmailOccupataTest() throws Exception {
        String email = "email";

        given(amministratoreService.controllaEmailOccupata(email)).willReturn(true);

        mockMvc.perform(get("/api/amministratore/controlla/email/" + email)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void controllaAmministratoreEsisteTest() throws Exception {

    }

    @Test
    public void aggiornaAmministratoreTest() throws Exception {

    }
}