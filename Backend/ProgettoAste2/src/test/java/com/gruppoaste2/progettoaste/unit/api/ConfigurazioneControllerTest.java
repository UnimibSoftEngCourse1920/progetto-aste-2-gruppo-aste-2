package com.gruppoaste2.progettoaste.unit.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruppoaste2.progettoaste.api.ConfigurazioneController;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import com.gruppoaste2.progettoaste.service.ConfigurazioneService;
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
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConfigurazioneController.class)
class ConfigurazioneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConfigurazioneService configurazioneService;

    // Test inserisciConfigurazione
    @DisplayName("inserisciConfigurazione non inserisce Configurazione")
    @Test
    void whenInserisciConfigurazione_givenAlreadyExistingConfigurazione_thenReturnEmptyJson() throws Exception {
        ConfigurazioneModel configurazione =
                new ConfigurazioneModel(null, "timeSlot", 3600, 10, 0.1,
                        Timestamp.valueOf(LocalDateTime.now()), Time.valueOf(LocalTime.now()));

        given(configurazioneService.inserisciConfigurazione(refEq(configurazione))).willReturn(null);

        mockMvc.perform(post("/api/configurazione/inserisci")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(configurazione)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    /*
    @DisplayName("inserisciConfigurazione inserisce Configurazione")
    @Test
    void whenInserisciConfigurazione_givenNonExistingConfigurazione_thenReturnJsonStringIdConfigurazione()
            throws Exception {
    */

    // Test eliminaConfigurazione
    @DisplayName("eliminaConfigurazione non elimina Configurazione")
    @Test
    void whenEliminaConfigurazione_givenNonExistingConfigurazione_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(configurazioneService.eliminaConfiguazione(id)).willReturn(0);

        mockMvc.perform(get("/api/configurazione/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("eliminaConfigurazione elimina Configurazione")
    @Test
    void whenEliminaConfigurazione_givenExistingConfigurazione_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(configurazioneService.eliminaConfiguazione(id)).willReturn(1);

        mockMvc.perform(get("/api/configurazione/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    // Test trovaConfigurazione
    @DisplayName("trovaConfigurazione non trova Configurazione")
    @Test
    void whenTrovaConfigurazione_givenNonExistingConfigurazione_thenReturnEmptyJson() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<ConfigurazioneModel> configurazioneTrovata = Optional.empty();

        given(configurazioneService.trovaConfigurazione(id)).willReturn(configurazioneTrovata);

        mockMvc.perform(get("/api/configurazione/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    // Test trovaConfigurazioni
    /*
    @DisplayName("trovaConfigurazione trova Configurazione")
    @Test
    void whenTrovaConfigurazione_givenExistingConfigurazione_thenReturnJsonMapConfigurazione() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<ConfigurazioneModel> configurazioneTrovata =
                Optional.of(new ConfigurazioneModel(id, "timeSlot", 3600, 10, 0.1, Timestamp.valueOf(LocalDateTime.now()), 11620));

        given(configurazioneService.trovaConfigurazione(id)).willReturn(configurazioneTrovata);

        mockMvc.perform(get("/api/configurazione/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(configurazioneTrovata.get().getId().toString()))
                .andExpect(jsonPath("$.tipoTimeSlot").value(configurazioneTrovata.get().getTipoTimeSlot()))
                .andExpect(jsonPath("$.maxTimeSlot").value(configurazioneTrovata.get().getMaxTimeSlot()))
                .andExpect(jsonPath("$.maxOfferte").value(configurazioneTrovata.get().getMaxOfferte()))
                .andExpect(jsonPath("$.penale").value(configurazioneTrovata.get().getPenale()))
                .andExpect(jsonPath("$.dataCreazione").value(configurazioneTrovata.get().getDataCreazione().toString()))
                .andExpect(jsonPath("$.durataTimeSlotFisso").value(configurazioneTrovata.get().getDurataTimeSlotFisso()));
    }*/

    @DisplayName("trovaConfigurazioni non trova Configurazioni")
    @Test
    void whenTrovaConfigurazioni_givenNonExistingConfigurazioni_thenReturnEmptyJsonArray() throws Exception {
        List<ConfigurazioneModel> configurazioniTrovate = Collections.emptyList();

        given(configurazioneService.trovaConfigurazioni()).willReturn(configurazioniTrovate);

        mockMvc.perform(get("/api/configurazione/configurazioni")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /*
    @DisplayName("trovaConfigurazioni trova Configurazioni")
    @Test
    void whenTrovaConfigurazioni_givenExistingConfigurazioni_thenReturnJsonArrayOfMapsConfigurazioni() throws Exception {
        UUID id = UUID.randomUUID();

        List<ConfigurazioneModel> configurazioniTrovate =
                Collections.singletonList(new ConfigurazioneModel(id, "timeSlot", 3600, 10, 0.1, Timestamp.valueOf(LocalDateTime.now()), 11620));

        given(configurazioneService.trovaConfigurazioni()).willReturn(configurazioniTrovate);

        mockMvc.perform(get("/api/configurazione/configurazioni")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(configurazioniTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].tipoTimeSlot").value(configurazioniTrovate.get(0).getTipoTimeSlot()))
                .andExpect(jsonPath("$[0].maxTimeSlot").value(configurazioniTrovate.get(0).getMaxTimeSlot()))
                .andExpect(jsonPath("$[0].maxOfferte").value(configurazioniTrovate.get(0).getMaxOfferte()))
                .andExpect(jsonPath("$[0].penale").value(configurazioniTrovate.get(0).getPenale()))
                .andExpect(jsonPath("$[0].dataCreazione").value(configurazioniTrovate.get(0).getDataCreazione().toString()))
                .andExpect(jsonPath("$[0].durataTimeSlotFisso").value(configurazioniTrovate.get(0).getDurataTimeSlotFisso()));
    }*/

    // Test trovaUltimaConfigurazione
    @DisplayName("trovaUltimaConfigurazione non trova UltimaConfigurazione")
    @Test
    void whenTrovaUltimaConfigurazione_givenNonExistingUltimaConfigurazione_thenReturnEmptyJson() throws Exception {

    }

    @DisplayName("trovaUltimaConfigurazione trova UltimaConfigurazione")
    @Test
    void whenTrovaUltimaConfigurazione_givenExistingUltimaConfigurazione_thenReturnJsonMapUltimaConfigurazione() throws Exception {

    }

    static ConfigurazioneModel makeConfigurazioneTest() {
        UUID id = UUID.randomUUID();
        return new ConfigurazioneModel(id, "fisso", 1, 1, 0.0,
                Timestamp.valueOf(LocalDateTime.now()), Time.valueOf(LocalTime.now()));
    }
}