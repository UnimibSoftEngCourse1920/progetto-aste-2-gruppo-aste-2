package com.gruppoaste2.progettoaste.service;

import com.gruppoaste2.progettoaste.dao.AstaDAO;
import com.gruppoaste2.progettoaste.dao.ConfigurazioneDAO;
import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ConditionalOnProperty(name = "scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Service
public class ScheduledUpdate {

    private final AstaDAO astaDAO;
    private final ConfigurazioneDAO configurazioneDAO;

    @Autowired
    public ScheduledUpdate(@Qualifier("postgres-asta") AstaDAO astaDAO,
                           @Qualifier("postgres-configurazione") ConfigurazioneDAO configurazioneDAO) {
        this.astaDAO = astaDAO;
        this.configurazioneDAO = configurazioneDAO;
    }

    @Scheduled(initialDelay = 1000L * 10, fixedDelay = 2000L)
    public void aggiornaSituazioneAste()
    {
        // System.out.println("aggiorna-asta");

        List<AstaModel> asteInCorso = astaDAO.trovaAsteInCorso();
        for(AstaModel asta : asteInCorso)
        {
            if(asta.getInfoAsta().getDataInizio() != null) {
                int numeroTimeSlot = 0;

                Optional<ConfigurazioneModel> conf = configurazioneDAO.trovaConfigurazione(asta.getConfigurazione().getId());

                if (conf.isPresent()) {
                    numeroTimeSlot = conf.get().getMaxTimeSlot();
                }
                Timestamp dataCreazione = asta.getInfoAsta().getDataInizio();
                // System.out.println("DataCreazione: " + dataCreazione + " long: " + dataCreazione.getTime());
                // System.out.println("Numero timeslot" + numeroTimeSlot);
                var durata = asta.getInfoAsta().getDurataTimeSlot().toLocalTime();
                // System.out.println("Duarata timeslot: " + "H: " + durata.getHour() + "M: " + durata.getMinute() + " S: " + durata.getSecond());
                long dataFine = dataCreazione.getTime() +
                        (numeroTimeSlot * (durata.getHour() * 3600000 + durata.getMinute() * 60000 + durata.getSecond() * 1000));
                // System.out.println("DataFine: " + new Timestamp(dataFine) + " long: " + dataFine);
                Timestamp now = Timestamp.from(Instant.now());

                if(dataFine < now.getTime())
                {
                    if(astaDAO.chiudiAsta(asta.getId()) == 0)
                        System.out.println("Errore: asta " + asta.getId() + " non chiusa");
                    else
                        System.out.println("Asta " + asta.getId() + " chiusa con successo");
                }
            }





        }

    }
}
