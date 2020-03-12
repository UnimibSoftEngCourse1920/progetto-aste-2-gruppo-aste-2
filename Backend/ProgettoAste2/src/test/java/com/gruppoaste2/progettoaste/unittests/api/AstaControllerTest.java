package com.gruppoaste2.progettoaste.unittests.api;

import com.gruppoaste2.progettoaste.api.AstaController;
import com.gruppoaste2.progettoaste.model.*;
import com.gruppoaste2.progettoaste.service.AstaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.sql.Date;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AstaController.class)
public class AstaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AstaService astaService;

    @Test
    public void whenTrovaAsta_givenNonExistingAsta_thenReturnEmptyJson() throws Exception{
        UUID id = UUID.randomUUID();

        Optional<AstaModel> astaTrovata = Optional.empty();

        given(astaService.trovaAsta(id)).willReturn(astaTrovata);

        mockMvc.perform(get("/api/asta/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    /*@Test
    public void whenTrovaAsta_givenExistingAsta_thenReturnJsonMapAsta() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url");
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Date.valueOf(LocalDate.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613", "boh1", 2));
        offerte.add(off1);

        Optional<AstaModel> astaTrovata = Optional.of(new AstaModel (idasta,
                new InfoAstaModel("info", 3.4,
                        Date.valueOf(LocalDate.now()),
                        Date.valueOf(LocalDate.now()),
                        0),
                        new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                                Date.valueOf(LocalDate.now()), 0,
                        oggetti,
                        new UtenteRegistratoModel(idUtente, "username", "email", "339025613", "boh", 0),
                        offerte));

        given(astaService.trovaAsta(idasta)).willReturn(astaTrovata);

        mockMvc.perform(get("/api/asta/" + idasta)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.id").value(astaTrovata.get().getId().toString()))
                .andExpect(jsonPath("$.infoAsta").isMap())
                .andExpect(jsonPath("$.infoAsta.tipo").value(astaTrovata.get().getInfoAsta().getTipo()))
                .andExpect(jsonPath("$.infoAsta.prezzoPartenza").value(astaTrovata.get().getInfoAsta().getPrezzoPartenza()))
                .andExpect(jsonPath("$.infoAsta.dataInizio").value(astaTrovata.get().getInfoAsta().getDataInizio().toString()))
                .andExpect(jsonPath("$.infoAsta.dataFine").value(astaTrovata.get().getInfoAsta().getDataFine().toString()))
                .andExpect(jsonPath("$.infoAsta.durataTimeSlot").value(astaTrovata.get().getInfoAsta().getDurataTimeSlot()))
                .andExpect(jsonPath("$.configurazione").isMap())
                .andExpect(jsonPath("$.configurazione.id").value(astaTrovata.get().getConfigurazione().getId().toString()))
                .andExpect(jsonPath("$.configurazione.maxTimeSlot").value(astaTrovata.get().getConfigurazione().getMaxTimeSlot()))
                .andExpect(jsonPath("$.configurazione.maxOfferte").value(astaTrovata.get().getConfigurazione().getMaxOfferte()))
                .andExpect(jsonPath("$.configurazione.penale").value(astaTrovata.get().getConfigurazione().getPenale()))
                .andExpect(jsonPath("$.configurazione.dataCreazione").value(astaTrovata.get().getConfigurazione().getDataCreazione().toString()))
                .andExpect(jsonPath("$.configurazione.durataTimeSlotFisso").value(astaTrovata.get().getConfigurazione().getDurataTimeSlotFisso()))
                .andExpect(jsonPath("$.oggetti").isArray())
                .andExpect(jsonPath("$.oggetti[0]").isMap())
                .andExpect(jsonPath("$.oggetti[0].id").value(astaTrovata.get().getOggetti().get(0).getId().toString()))
                .andExpect(jsonPath("$.oggetti[0].nome").value(astaTrovata.get().getOggetti().get(0).getNome()))
                .andExpect(jsonPath("$.oggetti[0].descrizione").value(astaTrovata.get().getOggetti().get(0).getDescrizione()))
                .andExpect(jsonPath("$.oggetti[0].urlImmagine").value(astaTrovata.get().getOggetti().get(0).getUrlImmagine()))
                .andExpect(jsonPath("$.astaManager").isMap())
                .andExpect(jsonPath("$.astaManager.id").value(astaTrovata.get().getAstaManager().getId().toString()))
                .andExpect(jsonPath("$.astaManager.username").value(astaTrovata.get().getAstaManager().getUsername()))
                .andExpect(jsonPath("$.astaManager.email").value(astaTrovata.get().getAstaManager().getEmail()))
                .andExpect(jsonPath("$.astaManager.numeroTelefono").value(astaTrovata.get().getAstaManager().getNumeroTelefono()))
                .andExpect(jsonPath("$.astaManager.password").value(astaTrovata.get().getAstaManager().getPassword()))
                .andExpect(jsonPath("$.astaManager.credito").value(astaTrovata.get().getAstaManager().getCredito()))
                .andExpect(jsonPath("$.offerte").isArray())
                .andExpect(jsonPath("$.offerte[0]").isMap())
                .andExpect(jsonPath("$.offerte[0].id").value(astaTrovata.get().getOfferte().get(0).getId().toString()))
                .andExpect(jsonPath("$.offerte[0].creditoOfferto").value(astaTrovata.get().getOfferte().get(0).getCreditoOfferto()))
                .andExpect(jsonPath("$.offerte[0].dataOfferta").value(astaTrovata.get().getOfferte().get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$.offerte[0].offerente").isMap())
                .andExpect(jsonPath("$.offerte[0]offerente.id").value(astaTrovata.get().getOfferte().get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$.offerte[0]offerente.username").value(astaTrovata.get().getOfferte().get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$.offerte[0]offerente.email").value(astaTrovata.get().getOfferte().get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$.offerte[0]offerente.numeroTelefono").value(astaTrovata.get().getOfferte().get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$.offerte[0]offerente.password").value(astaTrovata.get().getOfferte().get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$.offerte[0]offerente.credito").value(astaTrovata.get().getOfferte().get(0).getOfferente().getCredito()));
    }*/

    @Test
    public void whenTrovaAste_givenNonExistingAste_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        given(astaService.trovaAste()).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    /*@Test
    public void whenTrovaAste_givenExistingAste_thenReturnJsonArrayOfMapsAste() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url");
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Date.valueOf(LocalDate.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613", "boh1", 2));
        offerte.add(off1);

        List<AstaModel> asteTrovate =
                Collections.singletonList(new AstaModel (idasta,
                        new InfoAstaModel("info", 3.4,
                                Date.valueOf(LocalDate.now()),
                                Date.valueOf(LocalDate.now()),
                                0),
                        new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                                Date.valueOf(LocalDate.now()), 0),
                        oggetti,
                        new UtenteRegistratoModel(idUtente, "username", "email", "339025613", "boh", 0),
                        offerte));
        given(astaService.trovaTutteAste()).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]").isMap())
                .andExpect(jsonPath("$[0].id").value(asteTrovate.get(0).getId().toString()))
                .andExpect(jsonPath("$[0].infoAsta").isMap())
                .andExpect(jsonPath("$[0].infoAsta.tipo").value(asteTrovate.get(0).getInfoAsta().getTipo()))
                .andExpect(jsonPath("$[0].infoAsta.prezzoPartenza").value(asteTrovate.get(0).getInfoAsta().getPrezzoPartenza()))
                .andExpect(jsonPath("$[0].infoAsta.dataInizio").value(asteTrovate.get(0).getInfoAsta().getDataInizio().toString()))
                .andExpect(jsonPath("$[0].infoAsta.dataFine").value(asteTrovate.get(0).getInfoAsta().getDataFine().toString()))
                .andExpect(jsonPath("$[0].infoAsta.durataTimeSlot").value(asteTrovate.get(0).getInfoAsta().getDurataTimeSlot()))
                .andExpect(jsonPath("$[0].configurazione").isMap())
                .andExpect(jsonPath("$[0].configurazione.id").value(asteTrovate.get(0).getConfigurazione().getId().toString()))
                .andExpect(jsonPath("$[0].configurazione.maxTimeSlot").value(asteTrovate.get(0).getConfigurazione().getMaxTimeSlot()))
                .andExpect(jsonPath("$[0].configurazione.maxOfferte").value(asteTrovate.get(0).getConfigurazione().getMaxOfferte()))
                .andExpect(jsonPath("$[0].configurazione.penale").value(asteTrovate.get(0).getConfigurazione().getPenale()))
                .andExpect(jsonPath("$[0].configurazione.dataCreazione").value(asteTrovate.get(0).getConfigurazione().getDataCreazione().toString()))
                .andExpect(jsonPath("$[0].configurazione.durataTimeSlotFisso").value(asteTrovate.get(0).getConfigurazione().getDurataTimeSlotFisso()))
                .andExpect(jsonPath("$[0].oggetti").isArray())
                .andExpect(jsonPath("$[0].oggetti[0]").isMap())
                .andExpect(jsonPath("$[0].oggetti[0].id").value(asteTrovate.get(0).getOggetti().get(0).getId().toString()))
                .andExpect(jsonPath("$[0].oggetti[0].nome").value(asteTrovate.get(0).getOggetti().get(0).getNome()))
                .andExpect(jsonPath("$[0].oggetti[0].descrizione").value(asteTrovate.get(0).getOggetti().get(0).getDescrizione()))
                .andExpect(jsonPath("$[0].oggetti[0].urlImmagine").value(asteTrovate.get(0).getOggetti().get(0).getUrlImmagine()))
                .andExpect(jsonPath("$[0].astaManager").isMap())
                .andExpect(jsonPath("$[0].astaManager.id").value(asteTrovate.get(0).getAstaManager().getId().toString()))
                .andExpect(jsonPath("$[0].astaManager.username").value(asteTrovate.get(0).getAstaManager().getUsername()))
                .andExpect(jsonPath("$[0].astaManager.email").value(asteTrovate.get(0).getAstaManager().getEmail()))
                .andExpect(jsonPath("$[0].astaManager.numeroTelefono").value(asteTrovate.get(0).getAstaManager().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].astaManager.password").value(asteTrovate.get(0).getAstaManager().getPassword()))
                .andExpect(jsonPath("$[0].astaManager.credito").value(asteTrovate.get(0).getAstaManager().getCredito()))
                .andExpect(jsonPath("$[0].offerte").isArray())
                .andExpect(jsonPath("$[0].offerte[0]").isMap())
                .andExpect(jsonPath("$[0].offerte[0].id").value(asteTrovate.get(0).getOfferte().get(0).getId().toString()))
                .andExpect(jsonPath("$[0].offerte[0].creditoOfferto").value(asteTrovate.get(0).getOfferte().get(0).getCreditoOfferto()))
                .andExpect(jsonPath("$[0].offerte[0].dataOfferta").value(asteTrovate.get(0).getOfferte().get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerte[0].offerente").isMap())
                .andExpect(jsonPath("$[0].offerte[0]offerente.id").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.username").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.email").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.numeroTelefono").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.password").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.credito").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getCredito()));
    }*/

    @Test
    public void whenEliminaAsta_givenNonExistingAsta_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(astaService.eliminaAsta(id)).willReturn(0);

        mockMvc.perform(get("/api/asta/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));

    }

    @Test
    public void whenEliminaAsta_givenExistingAsta_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(astaService.eliminaAsta(id)).willReturn(1);

        mockMvc.perform(get("/api/asta/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }
}
