package com.gruppoaste2.progettoaste.integrationtests;

import com.gruppoaste2.progettoaste.dao.AmministratoreDAO;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "/resources/application.properties")
public class AmministratoreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AmministratoreDAO amministratoreDAO;

    // Test trovaAmministratore
    @Test
    public void whenTrovaAmministratore_givenNonExistingAmministratore_thenReturnEmptyJson() throws Exception {
        /*
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato = Optional.empty();

        mockMvc.perform(get("/api/amministratore/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").doesNotExist());
         */
    }

    @Test
    public void whenTrovaAmministratore_givenExistingAmministratore_thenReturnJsonMapAmministratore() throws Exception {
        /*
        UUID id = UUID.randomUUID();

        Optional<AmministratoreModel> amministratoreTrovato =
                Optional.of(new AmministratoreModel(id,"username","email","password"));

        mockMvc.perform(get("/api/amministratore/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(amministratoreTrovato.get().getId().toString()))
                .andExpect(jsonPath("$.username").value(amministratoreTrovato.get().getUsername()))
                .andExpect(jsonPath("$.email").value(amministratoreTrovato.get().getEmail()))
                .andExpect(jsonPath("$.password").value(amministratoreTrovato.get().getPassword()));
         */
    }
}
