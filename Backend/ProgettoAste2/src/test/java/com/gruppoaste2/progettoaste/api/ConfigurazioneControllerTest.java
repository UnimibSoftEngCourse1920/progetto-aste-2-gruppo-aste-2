package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import com.gruppoaste2.progettoaste.model.UtenteRegistratoModel;
import com.gruppoaste2.progettoaste.service.ConfigurazioneService;
import com.gruppoaste2.progettoaste.service.UtenteRegistratoService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.sql.Time;
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
@WebMvcTest(ConfigurazioneController.class)
class ConfigurazioneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigurazioneService configurazioneService;

    @Test
    void inserisciConfigurazione() {
    }

    // Test eliminaUtenteRegistrato
    @Test
    public void whenEliminaConfigurazione_givenNonExistingConfigurazione_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(configurazioneService.eliminaConfiguazione(id)).willReturn(0);

        mockMvc.perform(get("/api/configurazione/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    public void whenEliminaConfigurazione_givenExistingConfigurazione_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(configurazioneService.eliminaConfiguazione(id)).willReturn(1);

        mockMvc.perform(get("/api/configurazione/elimina/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test trovaConfigurazione
    @Test
    public void whenTrovaConfigurazione_givenNotExistingConfigurazione_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<ConfigurazioneModel> configurazioneTrovata = Optional.ofNullable(null);

        given(configurazioneService.trovaConfigurazione(id)).willReturn(configurazioneTrovata);

        mockMvc.perform(get("/api/configurazione/" + id.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void whenConfigurazione_givenExistingConfigurazione_thenReturnJsonConfigurazione() throws Exception {

    }

    // Test trovaConfigurazioni
    @Test
    public void whenTrovaConfigurazioni_givenNotExistingConfigurazioni_thenReturnEmptyJson() throws Exception {
        List<ConfigurazioneModel> configurazioniTrovate = Arrays.asList();

        given(configurazioneService.trovaConfigurazioni()).willReturn(Optional.of(configurazioniTrovate));

        mockMvc.perform(get("/api/configurazione/configurazioni")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void whenTrovaConfigurazioni_givenExistingConfigurazioni_thenReturnJsonArray() throws Exception {

    }

    // Test trovaUltimaConfigurazione
    @Test
    public void whenTrovaUltimaConfigurazione_givenNotExistingUltimaConfigurazione_thenReturnEmptyJson() throws Exception {

    }

    @Test
    public void whenUltimaConfigurazione_givenExistingUltimaConfigurazione_thenReturnJsonConfigurazione() throws Exception {

    }
}