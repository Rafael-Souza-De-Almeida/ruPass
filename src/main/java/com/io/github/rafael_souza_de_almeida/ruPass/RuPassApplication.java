package com.io.github.rafael_souza_de_almeida.ruPass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RuPassApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuPassApplication.class, args);
	}

}
