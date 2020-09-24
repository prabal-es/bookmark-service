package com.github.bookmark.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfiguration {

	@Value("${springdoc.swagger-ui.info.title}")
	private String title;
	@Value("${springdoc.swagger-ui.info.version}")
	private String version = "BOOKMARK version";
	@Value("${springdoc.swagger-ui.info.description}")
	private String description;
	@Value("${springdoc.swagger-ui.info.termsOfServiceUrl}")
	private String termsOfServiceUrl;
	@Value("${springdoc.swagger-ui.info.license}")
	private String license;
	@Value("${springdoc.swagger-ui.info.licenseUrl}")
	private String licenseUrl;
	@Value("${springdoc.swagger-ui.info.contactName}")
	private String contactName;
	@Value("${springdoc.swagger-ui.info.contactUrl}")
	private String contactUrl;
	@Value("${springdoc.swagger-ui.info.contactEmail}")
	private String contactEmail;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title(title).version(version).description(description)
				.termsOfService(termsOfServiceUrl).license(new License().name(license).url(licenseUrl))
				.contact(new Contact().name(contactName).url(contactUrl).email(contactEmail)));
	}

	@Bean
	public GroupedOpenApi companyGroupOpenApi() {
		String[] paths = { "/api/*/companies*/**" };
		return GroupedOpenApi.builder().setGroup("company").pathsToMatch(paths).build();
	}
	
	@Bean
	public GroupedOpenApi userGroupOpenApi() {
		String[] paths = { "/api/*/users*/**" };
		return GroupedOpenApi.builder().setGroup("user").pathsToMatch(paths).build();
	}
	
	@Bean
	public GroupedOpenApi cardGroupOpenApi() {
		String[] paths = { "/api/*/cards*/**" };
		return GroupedOpenApi.builder().setGroup("card").pathsToMatch(paths).build();
	}
	
	@Bean
	public GroupedOpenApi groupCardGroupOpenApi() {
		String[] paths = { "/api/*/groups*/**" };
		return GroupedOpenApi.builder().setGroup("group").pathsToMatch(paths).build();
	}
	
	@Bean
	public GroupedOpenApi tinyGroupOpenApi() {
		String[] paths = { "/*/*" };
		return GroupedOpenApi.builder().setGroup("tiny").pathsToMatch(paths).build();
	}
}
