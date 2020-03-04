package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.model.OffertaModel;
import com.gruppoaste2.progettoaste.service.AstaService;
import com.gruppoaste2.progettoaste.service.OffertaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
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
@WebMvcTest(AstaController.class)
class AstaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AstaService astaService;

    @Test
    void esempioGet() {
    }

    // Test trovaAsta
    @Test
    public void whenTrovaAsta_givenNotExistingAsta_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AstaModel> astaTrovata = Optional.ofNullable(null);

        given(astaService.trovaAsta(id)).willReturn(astaTrovata);

        mockMvc.perform(get("/api/asta/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenTrovaAsta_givenExistingAsta_thenReturnJsonAsta() throws Exception {

    }

    // Test trovaAste
    @Test
    public void whenTrovaAste_givenNotExistingAste_thenReturnEmptyJson() throws Exception {
        List<AstaModel> asteTrovate = Arrays.asList();

        given(astaService.trovaTutteAste()).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaAste_givenExistingAste_thenReturnJsonArray() throws Exception {

    }

    // Test aggiungiAsta
    @Test
    public void whenAggiungiAsta_givenExistingAsta_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenAggiungiAsta_givenNonExistingAsta_thenReturnJsonNumber1() throws Exception {

    }

    // Test aggiornaAsta
    @Test
    public void whenAggiornaAsta_givenNonExistingAsta_thenReturnJsonNumber0() throws Exception {

    }

    @Test
    public void whenAggiornaAsta_givenExistingAsta_thenReturnJsonNumber1() throws Exception {

    }

    // Test eliminaAsta
    @Test
    public void whenEliminaAsta_givenNonExistingAsta_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(astaService.eliminaAsta(id)).willReturn(0);

        mockMvc.perform(get("/api/asta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    public void whenEliminaAsta_givenExistingAsta_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(astaService.eliminaAsta(id)).willReturn(1);

        mockMvc.perform(get("/api/asta/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }
}