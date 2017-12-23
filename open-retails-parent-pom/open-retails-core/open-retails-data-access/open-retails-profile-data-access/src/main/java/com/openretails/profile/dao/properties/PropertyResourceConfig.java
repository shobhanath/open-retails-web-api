package com.openretails.profile.dao.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@PropertySource("classpath:profile.properties")
@Getter
public class PropertyResourceConfig {
	@Value("${openretails.database.salt}")
	private String dbSalt;

}
