package com.gruppoaste2.progettoaste;

import com.gruppoaste2.progettoaste.dao.AstaDAO;
import com.gruppoaste2.progettoaste.dao.ConfigurazioneDAO;
import com.gruppoaste2.progettoaste.model.AstaModel;
import com.gruppoaste2.progettoaste.model.ConfigurazioneModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduledUpdate {

    private final AstaDAO astaDAO;
    private final ConfigurazioneDAO configurazioneDAO;

    @Autowired
    public ScheduledUpdate(@Qualifier("postgres-asta") AstaDAO astaDAO,@Qualifier("postgres-configurazione") ConfigurazioneDAO configurazioneDAO) {
        this.astaDAO = astaDAO;
        this.configurazioneDAO = configurazioneDAO;
    }

    public void aggiornaSituazioneAste()
    {
        System.out.println("aggiorna-asta");
        Optional<ConfigurazioneModel> ultimaConfigurazione = configurazioneDAO.trovaUltimaConfigurazione();
        int numeroTimeSlot = 0;

        if(ultimaConfigurazione.isPresent())
        {
            numeroTimeSlot = ultimaConfigurazione.get().getMaxTimeSlot();
            System.out.println("numero timeslot: " + numeroTimeSlot);

        }
        List<AstaModel> asteInCorso = astaDAO.trovaAsteInCorso();
        for(AstaModel asta : asteInCorso)
        {
            if(asta.getInfoAsta().getDataFine() == null)
            {
                Timestamp dataCreazione = asta.getInfoAsta().getDataInizio();
                System.out.println("DataCreazione: " + dataCreazione + " long: " + dataCreazione.getTime());
                long dataFine = dataCreazione.getTime() + (numeroTimeSlot * asta.getInfoAsta().getDurataTimeSlot().getTime());
                System.out.println("DataFine: " + new Timestamp(dataFine) + " long: " + dataFine);
                Timestamp now = Timestamp.from(Instant.now());
                if(dataFine < now.getTime())
                {
                    astaDAO.chiudiAsta(asta.getId());
                    System.out.println("Chiuso asta" + asta.getId());
                }
            }
        }
    }

}
