package com.jpa.api.project.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {

	@Bean
	public OpenAPI openAPI() {

		return new OpenAPI()
				.info(new Info().title("Student API").description("This is Student Api development By Pritam Ray")
						.version("1.0")
						.contact(new Contact().name("Pritam Ray").email("raypritam49000@gamil.com")
								.url("https://raypritam.com"))
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("SpringShop Wiki Documentation")
						.url("https://springshop.wiki.github.org/docs"));
	}

}