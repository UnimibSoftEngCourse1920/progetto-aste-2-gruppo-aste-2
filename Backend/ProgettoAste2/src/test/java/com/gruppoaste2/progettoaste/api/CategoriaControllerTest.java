package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.service.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
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

@RunWith(SpringRunner.class)
@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    // Test trovaCategoria
    @Test
    public void whenTrovaCategoria_givenNonExistingCategoria_thenReturnEmptyJson() throws Exception {

    }

    @Test
    public void whenTrovaCategoria_givenExistingCategoria_thenReturnJsonMapCategoria() throws Exception {

    }

    // Test trovaCategorie
    @Test
    public void whenTrovaCategorie_givenNonExistingCategorie_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaCategorie_givenExistingCategorie_thenReturnJsonArrayOfMapsCategorie() throws Exception {

    }

    // Test inserisciCategoria
    @Test
    public void whenInserisciAmministratore_givenExistingAmministratore_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenInserisciAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber1() throws Exception {

    }

    // Test aggiornaCategoria
    @Test
    public void whenAggiornaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenAggiornaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {

    }

    // Test eliminaCategoria
    @Test
    public void whenEliminaAmministratore_givenNonExistingAmministratore_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenEliminaAmministratore_givenExistingAmministratore_thenReturnJsonNumber1() throws Exception {

    }

    // Test trovaAttributiCategoria
    @Test
    public void whenTrovaAttributiCategoria_givenNonExistingAttributiCategoria_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaAttributiCategoria_givenExistingAttributiCategoria_thenReturnJsonArrayOfMapsAttributiCategoria() throws Exception {

    }

    // Test trovaCategorieOggetto
    @Test
    public void whenTrovaCategorieOggetto_givenNonExistingCategorieOggetto_thenReturnEmptyJsonArray() throws Exception {

    }

    @Test
    public void whenTrovaCategorieOggetto_givenExistingCategorieOggetto_thenReturnJsonArrayOfMapsCategorieOggetto() throws Exception {

    }

    // Test valoreAttributiOggetto
    @Test
    void valoreAttributoOggetto() {
    }
}