package com.alami.demo;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties(value = "spring.datasource")
public class DbProfilesConfig {

	org.slf4j.Logger LOG_CONTROLLER = LoggerFactory.getLogger("auditcontroller");

	@Value("${spring.datasource.url}")
	private String url;

	@Profile("dev")
	@Bean
	public String devDatabaseConnection() {
		LOG_CONTROLLER.info("DB Connection FOR Development has Started!");
		LOG_CONTROLLER.info(url);
		return "DB Connection FOR Development has Started!";

	}

	@Profile("prod")
	@Bean
	public String prodDatabaseConnection() {
		LOG_CONTROLLER.info("DB Connection FOR Production has Started!");
		LOG_CONTROLLER.info(url);
		return "DB Connection FOR Development has Started!";

	}

}
