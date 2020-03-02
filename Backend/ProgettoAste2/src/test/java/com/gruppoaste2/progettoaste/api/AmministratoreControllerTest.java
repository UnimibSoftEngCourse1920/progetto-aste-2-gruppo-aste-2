package com.gruppoaste2.progettoaste.api;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gruppoaste2.progettoaste.service.AmministratoreService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.UUID;

@WebMvcTest(AmministratoreController.class)
class AmministratoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmministratoreService amministratoreService;

    @Test
    public void inserisciAmministratoreShouldReturn0() throws Exception {

    }

    @Test
    public void inserisciAmministratoreShouldReturn1() throws Exception {

    }

    @Test
    public void trovaAmministratoreShouldReturnNull() throws Exception {
        UUID id = UUID.randomUUID();
        when(amministratoreService.trovaAmministratore(id)).thenReturn(Optional.empty());
    }

    @Test
    public void trovaAmministratoreShouldReturnAmministratoreWithId() throws Exception {
        /*
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/amministratore/" + provaId).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        String expected = "{id:" + provaId + ",username:username,email:email,password:password}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);

         */
    }

    @Test
    public void trovaAmministratoriTest() throws Exception {

    }

    @Test
    public void controllaUsernameOccupatoTest() throws Exception {

    }

    @Test
    public void eliminaAmministratoreTest() throws Exception {

    }

    @Test
    public void controllaEmailOccupataTest() throws Exception {

    }

    @Test
    public void controllaAmministratoreEsisteTest() throws Exception {

    }

    @Test
    public void aggiornaAmministratoreTest() throws Exception {

    }
}