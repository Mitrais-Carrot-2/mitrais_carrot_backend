package com.team.two.mitrais_carrot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.team.two.mitrais_carrot")
public class MitraisCarrotBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(MitraisCarrotBackendApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
				// registry.addMapping("/").allowedOrigins("http://localhost:3000");
			}
		};
	}
}
