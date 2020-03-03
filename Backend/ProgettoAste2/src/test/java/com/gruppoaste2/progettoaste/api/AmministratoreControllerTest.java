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

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AmministratoreController.class)
class AmministratoreControllerTest {

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
    public void whenTrovaAmministratore_givenNotExistingAmministratore_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato = Optional.ofNullable(null);

        given(amministratoreService.trovaAmministratore(id)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenTrovaAmministratore_givenExistingAmministratore_thenReturnJsonAmministratore() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato =
                Optional.ofNullable(new AmministratoreModel(id,"username","email","password"));

        given(amministratoreService.trovaAmministratore(id)).willReturn(amministratoreTrovato);

        mockMvc.perform(get("/api/amministratore/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(amministratoreTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(amministratoreTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(amministratoreTrovato.get().getEmail()))
                .andExpect(jsonPath("$.password").value(amministratoreTrovato.get().getPassword()));
    }

    @Test
    public void whenTrovaAmministratori_givenNotExistingAmministratori_thenReturnEmptyJson() throws Exception {
        List<AmministratoreModel> amministratoriTrovati = Arrays.asList();

        given(amministratoreService.trovaAmministratori()).willReturn(amministratoriTrovati);

        mockMvc.perform(get("/api/amministratore/amministratori")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaAmministratori_givenExistingAmministratori_thenReturnJsonArray() throws Exception {
        UUID id = UUID.randomUUID();

        List<AmministratoreModel> amministratoriTrovati =
                Arrays.asList(new AmministratoreModel(id,"username","email","password"));

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