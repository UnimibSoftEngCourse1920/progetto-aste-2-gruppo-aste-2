package com.gruppoaste2.progettoaste.api;

import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import com.gruppoaste2.progettoaste.service.AmministratoreService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(AmministratoreController.class)
class AmministratoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmministratoreService amministratoreService;

    @Test
    void inserisciAmministratoreTest() {
    }

    @Test
    void trovaAmministratoreTest() throws Exception {
        UUID provaId = UUID.randomUUID();
        AmministratoreModel amministratoreModel = new AmministratoreModel(provaId, "username", "email", "password");

        Mockito.when(amministratoreService.trovaAmministratore(provaId)).thenReturn(java.util.Optional.of(amministratoreModel));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/amministratore/" + provaId).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "{id:" + provaId + ",username:username,email:email,password:password}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
    }

    @Test
    void trovaAmministratoriTest() {
    }

    @Test
    void controllaUsernameOccupatoTest() {
    }

    @Test
    void eliminaAmministratoreTest() {
    }

    @Test
    void controllaEmailOccupataTest() {
    }

    @Test
    void controllaAmministratoreEsisteTest() {
    }

    @Test
    void aggiornaAmministratoreTest() {
    }
}