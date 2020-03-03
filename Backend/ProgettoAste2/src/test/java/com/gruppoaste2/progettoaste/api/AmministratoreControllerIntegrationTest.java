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

import java.util.Optional;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
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

    }

    @Test
    public void controllaUsernameOccupatoTest() throws Exception {

    }

    @Test
    public void eliminaAmministratoreTest() throws Exception {

    }

    @Test
    public void controllaEmailOccupataTest() throws Exception {

    }

    @Test
    public void controllaAmministratoreEsisteTest() throws Exception {

    }

    @Test
    public void aggiornaAmministratoreTest() throws Exception {

    }
}