package com.gruppoaste2.progettoaste;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class ProgettoAsteApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProgettoAsteApplication.class, args);
	}
}
