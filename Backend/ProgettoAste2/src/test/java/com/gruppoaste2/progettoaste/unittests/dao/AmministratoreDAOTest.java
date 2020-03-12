package com.gruppoaste2.progettoaste.unittests.dao;

import com.gruppoaste2.progettoaste.dao.AmministratoreDAO;
import com.gruppoaste2.progettoaste.model.AmministratoreModel;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AmministratoreDAOTest {

    /*
    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = TestPostgreSQLContainer.getInstance();

    @Autowired
    private static AmministratoreDAO amministratoreDAO;

    private static final UUID id = UUID.randomUUID();
    private static final AmministratoreModel amministratore =
            new AmministratoreModel(id, "username", "email", "password");

    @BeforeClass
    public static void setUp() {
        amministratoreDAO.inserisciAmministratore(id, amministratore);
    }

    @AfterClass
    public static void tearDown() {
        amministratoreDAO.eliminaAmministratore(id);
    }

    @Test
    @Transactional
    public void whenTrovaAmministratore_givenExistingAmministratore_thenReturnAmministratore() {
        AmministratoreModel amministratoreTrovato = amministratoreDAO.trovaAmministratore(id)
                .orElse(null);
        assertThat(amministratoreTrovato).isEqualTo(amministratore);
    }
     */
}
