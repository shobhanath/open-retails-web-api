package com.openretails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EnableSwagger2
public class ProfileApplication {

	private static final Logger log = LoggerFactory.getLogger(ProfileApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProfileApplication.class);
		log.debug("SpringBootProfileApplication is started");
	}


}