package com.gruppoaste2.progettoaste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class ProgettoAsteApplication {

	private final ScheduledUpdate scheduledUpdate;

	@Autowired
	public ProgettoAsteApplication(ScheduledUpdate scheduledUpdate) {
		this.scheduledUpdate = scheduledUpdate;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProgettoAsteApplication.class, args);
	}

	@Scheduled(initialDelay = 1000L, fixedDelay = 2000L)
	public void callUpdate()
	{
		scheduledUpdate.aggiornaSituazioneAste();
	}
}
