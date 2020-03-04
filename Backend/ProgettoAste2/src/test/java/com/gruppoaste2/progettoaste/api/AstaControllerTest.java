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
    public void whenTrovaAsta_givenExistingAsta_thenReturnJsonMapAsta() throws Exception {
        UUID id = UUID.randomUUID();

        Optional<AstaModel> astaTrovata = Optional.of(new AstaModel (id,
                new InfoAsta("info", 3.4,
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now()),
                        0),
                        new ConfigurazioneModel(id2, )));
    }
}
