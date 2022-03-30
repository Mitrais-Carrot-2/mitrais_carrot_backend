package com.team.two.mitrais_carrot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.team.two.mitrais_carrot")
public class MitraisCarrotBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MitraisCarrotBackendApplication.class, args);
	}
}
