package com.gruppoaste2.progettoaste.unittests.api;

import com.gruppoaste2.progettoaste.api.OggettoController;
import com.gruppoaste2.progettoaste.service.OggettoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OggettoController.class)
class OggettoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OggettoService oggettoService;


}