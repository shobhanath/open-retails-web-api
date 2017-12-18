package com.openretails.stock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EnableSwagger2
@EnableCaching
public class Startup {

	private static final Logger log = LoggerFactory.getLogger(Startup.class);

	public static void main(String[] args) {
		SpringApplication.run(Startup.class);
		log.debug("Stock spring boot app is started");
	}


}