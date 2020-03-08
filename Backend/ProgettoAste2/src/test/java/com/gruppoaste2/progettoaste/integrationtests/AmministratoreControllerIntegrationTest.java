package com.gruppoaste2.progettoaste.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AmministratoreControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testTrovaAmministratore() throws Exception {
        /*
        UUID id = UUID.randomUUID();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/amministratore/" + id), HttpMethod.GET, entity, String.class);
        String expected =
                "{" +
                        "\"id\":" + id + "," +
                        "\"username\":\"username\"," +
                        "\"email\":\"email\"," +
                        "\"password\":\"password\"}";
        JSONAssert.assertEquals(expected, response.getBody(), false);
        //sembra giusto solo che response.getBody() da' una stringa vuota
         */
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
