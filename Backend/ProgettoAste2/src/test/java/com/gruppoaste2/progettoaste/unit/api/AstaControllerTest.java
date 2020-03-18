package com.gruppoaste2.progettoaste.unit.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gruppoaste2.progettoaste.api.AstaController;
import com.gruppoaste2.progettoaste.model.*;
import com.gruppoaste2.progettoaste.service.AstaService;
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
@WebMvcTest(AstaController.class)
class AstaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AstaService astaService;

    // Test aggiungiAsta
    @DisplayName("aggiungiAsta non aggiunge Asta")
    @Test
    void whenAggiungiAsta_givenAlreadyExistingAsta_thenReturnEmptyJson() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        List<CategoriaModel> categorie = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url", Collections.emptyList()); // todo andre empty list
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Timestamp.valueOf(LocalDateTime.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613",
                        "boh1", 2, false, false));
        offerte.add(off1);

        AstaModel asta = new AstaModel(idasta,
                new InfoAstaModel("info", 3.4,
                        Timestamp.valueOf(LocalDateTime.now()),
                        Timestamp.valueOf(LocalDateTime.now()),
                        Time.valueOf(LocalTime.now()),
                        false,
                        "max_timeslot"),
                new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                        Timestamp.valueOf(LocalDateTime.now()), Time.valueOf(LocalTime.now())),
                oggetti,
                new UtenteRegistratoModel(idUtente, "username", "email",
                        "339025613", "boh", 0, false, false),
                offerte);

        given(astaService.aggiungiAsta(refEq(asta))).willReturn(null);

        mockMvc.perform(post("/api/asta/aggiungi")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(asta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    /*
    @DisplayName("aggiungiAsta aggiunge Asta")
    @Test
    void whenAggiungiAsta_givenNonExistingAsta_thenReturnJsonStringIdAsta() throws Exception
    */

    //eliminaAsta
    @DisplayName("eliminaAsta non elimina Asta")
    @Test
    void whenEliminaAsta_givenNonExistingAsta_thenReturnJsonNumber0() throws Exception {
        UUID id = UUID.randomUUID();

        given(astaService.eliminaAsta(id)).willReturn(0);

        mockMvc.perform(get("/api/asta/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    @DisplayName("eliminaAsta elimina Asta")
    @Test
    void whenEliminaAsta_givenExistingAsta_thenReturnJsonNumber1() throws Exception {
        UUID id = UUID.randomUUID();

        given(astaService.eliminaAsta(id)).willReturn(1);

        mockMvc.perform(get("/api/asta/elimina/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    //trovaAsta
    @DisplayName("trovaAsta non trova Asta")
    @Test
    void whenTrovaAsta_givenNonExistingAsta_thenReturnEmptyJson() throws Exception{
        UUID id = UUID.randomUUID();

        Optional<AstaModel> astaTrovata = Optional.empty();

        given(astaService.trovaAsta(id)).willReturn(astaTrovata);

        mockMvc.perform(get("/api/asta/" + id)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @DisplayName("trovaAsta trova Asta")
    @Test
    void whenTrovaAsta_givenExistingAsta_thenReturnJsonMapAsta() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url", Collections.emptyList()); // todo andre empty list
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Timestamp.valueOf(LocalDateTime.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613",
                        "boh1", 2, false, false));
        offerte.add(off1);

        Optional<AstaModel> astaTrovata = Optional.of(new AstaModel (idasta,
                new InfoAstaModel("info", 3.4,
                        Timestamp.valueOf(LocalDateTime.now()),
                        Timestamp.valueOf(LocalDateTime.now()),
                        Time.valueOf(LocalTime.now()),
                        false,
                        "max_timeslot"),
                        new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                                Timestamp.valueOf(LocalDateTime.now()), Time.valueOf(LocalTime.now())),
                        oggetti,
                        new UtenteRegistratoModel(idUtente, "username", "email",
                                "339025613", "boh", 0, false, false),
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
                //.andExpect(jsonPath("$.infoAsta.dataInizio").value(astaTrovata.get().getInfoAsta().getDataInizio().toString()))
                //.andExpect(jsonPath("$.infoAsta.dataFine").value(astaTrovata.get().getInfoAsta().getDataFine().toString()))
                .andExpect(jsonPath("$.infoAsta.durataTimeSlot").value(astaTrovata.get().getInfoAsta().getDurataTimeSlot().toString()))
                .andExpect(jsonPath("$.configurazione").isMap())
                .andExpect(jsonPath("$.configurazione.id").value(astaTrovata.get().getConfigurazione().getId().toString()))
                .andExpect(jsonPath("$.configurazione.maxTimeSlot").value(astaTrovata.get().getConfigurazione().getMaxTimeSlot()))
                .andExpect(jsonPath("$.configurazione.maxOfferte").value(astaTrovata.get().getConfigurazione().getMaxOfferte()))
                .andExpect(jsonPath("$.configurazione.penale").value(astaTrovata.get().getConfigurazione().getPenale()))
                //.andExpect(jsonPath("$.configurazione.dataCreazione").value(astaTrovata.get().getConfigurazione().getDataCreazione().toString()))
                .andExpect(jsonPath("$.configurazione.durataTimeSlotFisso").value(astaTrovata.get().getConfigurazione().getDurataTimeSlotFisso().toString()))
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
                //.andExpect(jsonPath("$.offerte[0].dataOfferta").value(astaTrovata.get().getOfferte().get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$.offerte[0].offerente").isMap())
                .andExpect(jsonPath("$.offerte[0]offerente.id").value(astaTrovata.get().getOfferte().get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$.offerte[0]offerente.username").value(astaTrovata.get().getOfferte().get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$.offerte[0]offerente.email").value(astaTrovata.get().getOfferte().get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$.offerte[0]offerente.numeroTelefono").value(astaTrovata.get().getOfferte().get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$.offerte[0]offerente.password").value(astaTrovata.get().getOfferte().get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$.offerte[0]offerente.credito").value(astaTrovata.get().getOfferte().get(0).getOfferente().getCredito()));
    }

    //trovaAste
    @DisplayName("trovaAste non trova Aste")
    @Test
    void whenTrovaAste_givenNonExistingAste_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        given(astaService.trovaAste()).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("trovaAste trova Aste")
    @Test
    void whenTrovaAste_givenExistingAste_thenReturnJsonArrayOfMapsAste() throws Exception {
        UUID idasta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url", Collections.emptyList()); // todo andre emty categorie
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Timestamp.valueOf(LocalDateTime.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613",
                        "boh1", 2, false, false));
        offerte.add(off1);

        List<AstaModel> asteTrovate =
                Collections.singletonList(new AstaModel (idasta,
                        new InfoAstaModel("info", 3.4,
                                Timestamp.valueOf(LocalDateTime.now()),
                                Timestamp.valueOf(LocalDateTime.now()),
                                Time.valueOf(LocalTime.now()),
                                false,
                                "max_timeslot"),
                        new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                                Timestamp.valueOf(LocalDateTime.now()), Time.valueOf(LocalTime.now())),
                        oggetti,
                        new UtenteRegistratoModel(idUtente, "username", "email",
                                "339025613", "boh", 0, false, false),
                        offerte));
        given(astaService.trovaAste()).willReturn(asteTrovate);

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
                //.andExpect(jsonPath("$[0].infoAsta.dataInizio").value(asteTrovate.get(0).getInfoAsta().getDataInizio().toString()))
                //.andExpect(jsonPath("$[0].infoAsta.dataFine").value(asteTrovate.get(0).getInfoAsta().getDataFine().toString()))
                .andExpect(jsonPath("$[0].infoAsta.durataTimeSlot").value(asteTrovate.get(0).getInfoAsta().getDurataTimeSlot().toString()))
                .andExpect(jsonPath("$[0].configurazione").isMap())
                .andExpect(jsonPath("$[0].configurazione.id").value(asteTrovate.get(0).getConfigurazione().getId().toString()))
                .andExpect(jsonPath("$[0].configurazione.maxTimeSlot").value(asteTrovate.get(0).getConfigurazione().getMaxTimeSlot()))
                .andExpect(jsonPath("$[0].configurazione.maxOfferte").value(asteTrovate.get(0).getConfigurazione().getMaxOfferte()))
                .andExpect(jsonPath("$[0].configurazione.penale").value(asteTrovate.get(0).getConfigurazione().getPenale()))
                //.andExpect(jsonPath("$[0].configurazione.dataCreazione").value(asteTrovate.get(0).getConfigurazione().getDataCreazione().toString()))
                .andExpect(jsonPath("$[0].configurazione.durataTimeSlotFisso").value(asteTrovate.get(0).getConfigurazione().getDurataTimeSlotFisso().toString()))
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
                //.andExpect(jsonPath("$[0].offerte[0].dataOfferta").value(asteTrovate.get(0).getOfferte().get(0).getDataOfferta().toString()))
                .andExpect(jsonPath("$[0].offerte[0].offerente").isMap())
                .andExpect(jsonPath("$[0].offerte[0]offerente.id").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getId().toString()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.username").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getUsername()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.email").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getEmail()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.numeroTelefono").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getNumeroTelefono()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.password").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getPassword()))
                .andExpect(jsonPath("$[0].offerte[0]offerente.credito").value(asteTrovate.get(0).getOfferte().get(0).getOfferente().getCredito()));
    }

    //trovaAsteInCorso
    @DisplayName("trovaAsteInCorso non trova AsteInCorso")
    @Test
    void whenTrovaAsteInCorso_givenNonExistingAsteInCorso_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        given(astaService.trovaAsteInCorso()).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/incorso/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    //trovaAsteInCorsoUtente
    @DisplayName("trovaAsteInCorsoUtente non trova AsteInCorsoUtente")
    @Test
    void whenTrovaAsteInCorsoUtente_givenNonExistingAsteInCorsoUtente_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        UUID idAstaManager = UUID.randomUUID();
        given(astaService.trovaAsteInCorsoUtente(idAstaManager)).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/incorso/" + idAstaManager)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    //trovaAsteScaduteUtente
    @DisplayName("trovaAsteScaduteUtente non trova AsteScaduteUtente")
    @Test
    void whenTrovaAsteScaduteUtente_givenNonExistingAsteScaduteUtente_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        UUID idAstaManager = UUID.randomUUID();
        given(astaService.trovaAsteScaduteUtente(idAstaManager)).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/scadute/" + idAstaManager)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    //trovaAsteInCorsoOfferente
    @DisplayName("trovaAsteInCorsoOfferente non trova AsteInCorsoOfferente")
    @Test
    void whenTrovaAsteInCorsoOfferente_givenNonExistingAsteInCorsoOfferente_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        UUID idOfferente = UUID.randomUUID();
        given(astaService.trovaAsteInCorsoOfferente(idOfferente)).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/incorso/offerente/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    //trovaAsteInCorsoBustaChiusaOfferente
    @DisplayName("trovaAsteInCorsoBustaChiusaOfferente non trova AsteInCorsoBustaChiusaOfferente")
    @Test
    void whenTrovaAsteInCorsoBustaChiusaOfferente_givenNonExistingAsteInCorsoBustaChiusaOfferente_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        UUID idOfferente = UUID.randomUUID();
        given(astaService.trovaAsteInCorsoBustaChiusaOfferente(idOfferente)).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/incorso/busta-chiusa/offerente/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    //trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente
    @DisplayName("trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente non trova AsteInCorsoSuperamentoImmediatoMassimoOfferente")
    @Test
    void whentrovaAsteInCorsoSuperamentoImmediatoMassimoOfferente_givenNonExistingAsteInCorsoSuperamentoImmediatoMassimoOfferente_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        UUID idOfferente = UUID.randomUUID();
        given(astaService.trovaAsteInCorsoSuperamentoImmediatoMassimoOfferente(idOfferente)).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/incorso/superamento-immediato/massimo-offerente/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    //trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato
    @DisplayName("trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato non trova AsteInCorsoSuperamentoImmediatoOfferenteSuperato")
    @Test
    void whentrovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato_givenNonExistingAsteInCorsoSuperamentoImmediatoOfferenteSuperato_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        UUID idOfferente = UUID.randomUUID();
        given(astaService.trovaAsteInCorsoSuperamentoImmediatoOfferenteSuperato(idOfferente)).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/incorso/superamento-immediato/offerente-superato/" + idOfferente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    //trovaAsteVinteDaUtente
    @DisplayName("trovaAsteVinteDaUtente non trova AsteVinteDaUtente")
    @Test
    void whenTrovaAsteVinteDaUtente_givenNonExistingAsteVinteDaUtente_thenReturnEmptyJsonArray() throws Exception {
        List<AstaModel> asteTrovate = Collections.emptyList();

        UUID idUtente = UUID.randomUUID();
        given(astaService.trovaAsteVinteDaUtente(idUtente)).willReturn(asteTrovate);

        mockMvc.perform(get("/api/asta/aste/vinte/" + idUtente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    // Test aggiornaAsta
    @DisplayName("aggiornaAsta non aggiorna Asta")
    @Test
    void whenAggiornaAsta_givenNonExistingAsta_thenReturnJsonNumber0() throws Exception {
        UUID idAsta = UUID.randomUUID();
        UUID idconf = UUID.randomUUID();
        UUID idUtente = UUID.randomUUID();
        UUID idOgge = UUID.randomUUID();
        UUID idoff = UUID.randomUUID();
        UUID idut2 = UUID.randomUUID();
        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url", Collections.emptyList()); // todo andre empty list
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Timestamp.valueOf(LocalDateTime.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613",
                        "boh1", 2, false, false));
        offerte.add(off1);

        AstaModel asta = new AstaModel(idAsta,
                new InfoAstaModel("info", 3.4,
                        Timestamp.valueOf(LocalDateTime.now()),
                        Timestamp.valueOf(LocalDateTime.now()),
                        Time.valueOf(LocalTime.now()),
                        false,
                        "max_timeslot"),
                new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                        Timestamp.valueOf(LocalDateTime.now()), Time.valueOf(LocalTime.now())),
                oggetti,
                new UtenteRegistratoModel(idUtente, "username", "email",
                        "339025613", "boh", 0, false, false),
                offerte);

        given(astaService.aggiornaAsta(refEq(idAsta), refEq(asta)))
                .willReturn(0);

        mockMvc.perform(post("/api/asta/aggiorna/" + idAsta)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .content(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
                        .writeValueAsString(asta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(0));
    }

    /*
    @DisplayName("aggiornaAsta aggiorna Asta")
    @Test
    void whenAggiornaAsta_givenExistingAsta_thenReturnJsonNumber1() throws Exception
     */

    private AstaModel makeAstaTest() {
        UUID idAsta = UUID.randomUUID();
        UUID idConfigurazione = UUID.randomUUID();
        UUID idAstaManager = UUID.randomUUID();
        UUID idOggetto = UUID.randomUUID();
        UUID idCategoria = UUID.randomUUID();
        UUID idAttributo = UUID.randomUUID();
        UUID idOfferta = UUID.randomUUID();
        UUID idOfferente = UUID.randomUUID();

        List<OggettoModel> oggetti = new ArrayList<>();
        List<OffertaModel> offerte = new ArrayList<>();
        List<CategoriaModel> categorie = new ArrayList<>();
        OggettoModel ogg1 = new OggettoModel(idOgge, "nome", "descrizione", "url", Collections.emptyList()); // todo andre empty list
        oggetti.add(ogg1);
        OffertaModel off1 = new OffertaModel(idoff, 1, Timestamp.valueOf(LocalDateTime.now()),
                new UtenteRegistratoModel(idut2, "username1", "email1", "+39339025613",
                        "boh1", 2, false, false));
        offerte.add(off1);

        AstaModel asta = new AstaModel(idasta,
                new InfoAstaModel("info", 3.4,
                        Timestamp.valueOf(LocalDateTime.now()),
                        Timestamp.valueOf(LocalDateTime.now()),
                        Time.valueOf(LocalTime.now()),
                        false,
                        "max_timeslot"),
                new ConfigurazioneModel(idconf, "fisso", 1, 4, 0.21,
                        Timestamp.valueOf(LocalDateTime.now()), Time.valueOf(LocalTime.now())),
                oggetti,
                new UtenteRegistratoModel(idUtente, "username", "email",
                        "339025613", "boh", 0, false, false),
                offerte);
        return asta;
    }
}
