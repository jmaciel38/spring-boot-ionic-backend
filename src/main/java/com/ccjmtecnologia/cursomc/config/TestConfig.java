package com.ccjmtecnologia.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ccjmtecnologia.cursomc.services.DBService;
import com.ccjmtecnologia.cursomc.services.EmailService;
import com.ccjmtecnologia.cursomc.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	@Bean
	boolean instantiateDatabase() throws ParseException {
		
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
	EmailService emailService() {
		return new MockEmailService();
	}
}
