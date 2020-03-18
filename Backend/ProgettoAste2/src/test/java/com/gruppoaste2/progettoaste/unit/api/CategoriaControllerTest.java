package com.gruppoaste2.progettoaste.unit.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruppoaste2.progettoaste.api.CategoriaController;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.model.AttributoModel;
import com.gruppoaste2.progettoaste.model.CategoriaModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.CategoriaService;
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
@WebMvcTest(CategoriaController.class)
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    // Test aggiungiCategoria
    @DisplayName("aggiungiCategoria non aggiunge Categoria")
    @Test
    void whenAggiungiCategoria_givenAlreadyExistingCategoria_thenReturnEmptyJson() throws Exception {
        String idCategoria = UUID.randomUUID().toString();
        UUID idAttributo = UUID.randomUUID();

        List<AttributoModel> attributi = new ArrayList<>();
        AttributoModel attributo = new AttributoModel(idAttributo, "a", "b");

        CategoriaModel categoria = new CategoriaModel(idCategoria, attributi);

        given(categoriaService.aggiungiCategoria(refEq(categoria))).willReturn(null);

        mockMvc.perform(post("/api/categoria/aggiungi")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("aggiungiCategoria aggiunge Categoria")
    @Test
    void whenAggiungiCategoria_givenNonExistingCategoria_thenReturnJsonStringIdCategoria() throws Exception {
        String idCategoria = UUID.randomUUID().toString();
        UUID idAttributo = UUID.randomUUID();

        List<AttributoModel> attributi = new ArrayList<>();
        AttributoModel attributo = new AttributoModel(idAttributo, "a", "b");

        CategoriaModel categoria = new CategoriaModel(idCategoria, attributi);

        given(categoriaService.aggiungiCategoria(refEq(categoria))).willReturn(idCategoria);

        mockMvc.perform(post("/api/categoria/aggiungi")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isString())
                .andExpect(jsonPath("$").value(idCategoria));
    }

    // Test eliminaCategoria
    @DisplayName("eliminaCategoria non elimina Categoria")
    @Test
    void whenEliminaCategoria_givenNonExistingCategoria_thenReturnJsonNumber0() throws Exception {
        String idCategoria = UUID.randomUUID().toString();

        given(categoriaService.eliminaCategoria(idCategoria)).willReturn(0);

        mockMvc.perform(get("/api/categoria/elimina/" + idCategoria)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("eliminaCategoria elimina Categoria")
    @Test
    void whenEliminaCategoria_givenExistingCategoria_thenReturnJsonNumber1() throws Exception {
        String idCategoria = UUID.randomUUID().toString();

        given(categoriaService.eliminaCategoria(idCategoria)).willReturn(1);

        mockMvc.perform(get("/api/categoria/elimina/" + idCategoria)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test trovaCategoria
    @DisplayName("trovaCategoria non trova Categoria")
    @Test
    void whenTrovaCategoria_givenNonExistingCategoria_thenReturnEmptyJson() throws Exception {
        String idCategoria = UUID.randomUUID().toString();

        Optional<CategoriaModel> categoriaTrovata = Optional.empty();

        given(categoriaService.trovaCategoria(idCategoria)).willReturn(categoriaTrovata);

        mockMvc.perform(get("/api/categoria/" + idCategoria)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("trovaCategoria trova Categoria")
    @Test
    void whenTrovaCategoria_givenExistingCategoria_thenReturnJsonMapCategoria() throws Exception {
        String idCategoria = UUID.randomUUID().toString();
        UUID idAttributo = UUID.randomUUID();

        List<AttributoModel> attributi = new ArrayList<>();
        AttributoModel attributo = new AttributoModel(idAttributo, "a", "b");

        Optional<CategoriaModel> categoriaTrovata =
                Optional.of(new CategoriaModel(idCategoria, attributi));

        given(categoriaService.trovaCategoria(idCategoria)).willReturn(categoriaTrovata);

        mockMvc.perform(get("/api/categoria/" + idCategoria)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(categoriaTrovata.get().getId()))
                .andExpect(jsonPath("$.attributi").value(categoriaTrovata.get().getAttributi()));
    }

    // Test trovaCategorie
    @DisplayName("trovaCategorie non trova Categorie")
    @Test
    void whenTrovaCategorie_givenNonExistingCategorie_thenReturnEmptyJsonArray() throws Exception {
        List<CategoriaModel> categorieTrovate = Collections.emptyList();

        given(categoriaService.trovaCategorie()).willReturn(categorieTrovate);

        mockMvc.perform(get("/api/categoria/categorie")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /*
    @DisplayName("trovaCategorie trova Categorie")
    @Test
    void whenTrovaCategorie_givenExistingCategorie_thenReturnJsonArrayOfMapsCategorie() throws Exception {
        String idCategoria = UUID.randomUUID().toString();
        UUID idAttributo = UUID.randomUUID();

        List<AttributoModel> attributi = new ArrayList<>();
        AttributoModel attributo = new AttributoModel(idAttributo, "a", "b");

        List<CategoriaModel> categorieTrovate =
                Collections.singletonList(new CategoriaModel(idCategoria, attributi));

        given(categoriaService.trovaCategorie()).willReturn(categorieTrovate);

        mockMvc.perform(get("/api/categoria/categorie/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$.id").value(categorieTrovate.get(0).getId()))
                .andExpect(jsonPath("$.attributi.id").value(categorieTrovate.get(0).getAttributi().get(0).getId()))
                .andExpect(jsonPath("$.attributi.nome").value(categorieTrovate.get(0).getAttributi().get(0).getNome()))
                .andExpect(jsonPath("$.attributi.valore").value(categorieTrovate.get(0).getAttributi().get(0).getValore()));
    }
    */

    // Test aggiornaCategoria
    @DisplayName("aggiornaCategoria non aggiorna Categoria")
    @Test
    void whenAggiornaCategoria_givenNonExistingCategoria_thenReturnJsonNumber0() throws Exception {
        String idCategoria = UUID.randomUUID().toString();
        UUID idAttributo = UUID.randomUUID();

        List<AttributoModel> attributi = new ArrayList<>();
        AttributoModel attributo = new AttributoModel(idAttributo, "a", "b");

        CategoriaModel categoria = new CategoriaModel(idCategoria, attributi);

        given(categoriaService.aggiornaCategoria(refEq(idCategoria), refEq(categoria))).willReturn(0);

        mockMvc.perform(post("/api/categoria/aggiorna/" + idCategoria)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("aggiornaCategoria aggiorna Categoria")
    @Test
    void whenAggiornaCategoria_givenExistingCategoria_thenReturnJsonNumber1() throws Exception {
        String idCategoria = UUID.randomUUID().toString();
        UUID idAttributo = UUID.randomUUID();

        List<AttributoModel> attributi = new ArrayList<>();
        AttributoModel attributo = new AttributoModel(idAttributo, "a", "b");

        CategoriaModel categoria = new CategoriaModel(idCategoria, attributi);

        given(categoriaService.aggiornaCategoria(refEq(idCategoria), refEq(categoria))).willReturn(1);

        mockMvc.perform(post("/api/categoria/aggiorna/" + idCategoria)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(categoria)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }
}