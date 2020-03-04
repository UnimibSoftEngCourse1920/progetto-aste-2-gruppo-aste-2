package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import com.gruppoaste2.progettoaste.model.InfoAsta;
import com.gruppoaste2.progettoaste.service.AstaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.*;
import java.sql.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

public class AstaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AstaService astaService;

    @Test
    public void whenTrovaAsta_givenNonExistingAsta_thenReturnEmptyJson() throws Exception{
        UUID id = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Optional<AstaModel> astaTrovata = Optional.empty();

        given(astaService.trovaAsta(id)).willReturn(astaTrovata);

        mockMvc.perform(get("/api/asta/" + id)
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
