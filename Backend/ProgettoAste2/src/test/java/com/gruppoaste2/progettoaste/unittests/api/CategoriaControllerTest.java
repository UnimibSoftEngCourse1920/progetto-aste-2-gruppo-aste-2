package com.gruppoaste2.progettoaste.unittests.api;

import com.gruppoaste2.progettoaste.api.CategoriaController;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    // Test trovaCategoria
    @Test
    void whenTrovaCategoria_givenNonExistingCategoria_thenReturnEmptyJson() throws Exception {

    }

    @Test
    void whenTrovaCategoria_givenExistingCategoria_thenReturnJsonMapCategoria() throws Exception {

    }

    // Test trovaCategorie
    @Test
    void whenTrovaCategorie_givenNonExistingCategorie_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    void whenTrovaCategorie_givenExistingCategorie_thenReturnJsonArrayOfMapsCategorie() throws Exception {

    }

    // Test inserisciCategoria
    @Test
    void whenInserisciAmministratore_givenExistingAmministratore_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenInserisciAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber1() throws Exception {

    }

    // Test aggiornaCategoria
    @Test
    void whenAggiornaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenAggiornaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {

    }

    // Test eliminaCategoria
    @Test
    void whenEliminaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    void whenEliminaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {

    }

    // Test trovaCategorieOggetto
    @Test
    void whenTrovaCategorieOggetto_givenNonExistingCategorieOggetto_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    void whenTrovaCategorieOggetto_givenExistingCategorieOggetto_thenReturnJsonArrayOfMapsCategorieOggetto() throws Exception {

    }
}