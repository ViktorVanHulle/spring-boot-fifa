package com.springBoot.firstExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import service.JpaVoetbalDao;
import service.VoetbalDao;
import validator.TicketValidation;


@SpringBootApplication
public class FifaWorldCupProjectApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FifaWorldCupProjectApplication.class, args);
	}
	
	
	@Bean
	public VoetbalDao voetbalService() {
		return new JpaVoetbalDao();
	}

	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("nl");
		return messageSource;
	}
	
	@Bean
	public TicketValidation ticketValidation() {
		return new TicketValidation();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("resources/css/");
		registry.addResourceHandler("/video/**").addResourceLocations("resources/video/");

	}
	
	
}
