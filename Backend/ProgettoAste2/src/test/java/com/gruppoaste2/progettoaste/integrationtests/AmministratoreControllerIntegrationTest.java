package com.gruppoaste2.progettoaste.integrationtests;

import com.gruppoaste2.progettoaste.dao.AmministratoreDAO;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
// @TestPropertySource(locations = "resources/application.properties")
class AmministratoreControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private static AmministratoreDAO amministratoreDAO;

    private static final UUID id = UUID.randomUUID();
    private static final AmministratoreModel amministratore =
            new AmministratoreModel(id, "username", "email", "password");

    @BeforeAll
    static void setUp() {
        amministratoreDAO.inserisciAmministratore(id, amministratore);
    }

    @AfterAll
    static void tearDown() {
        amministratoreDAO.eliminaAmministratore(id);
    }

    // Test trovaAmministratore
    @Test
    void whenTrovaAmministratore_givenNonExistingAmministratore_thenReturnEmptyJson() throws Exception {
        /*
        UUID id = UUID.randomUUID();

        mockMvc.perform(get("/api/amministratore/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").doesNotExist());
         */
    }

    @Test
    void whenTrovaAmministratore_givenExistingAmministratore_thenReturnJsonMapAmministratore() throws Exception {
        /*
        mockMvc.perform(get("/api/amministratore/" + this.id)
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
