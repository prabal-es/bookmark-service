package com.socgen.bookmark.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.socgen.bookmark.BookmarkServiceApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, /*value = {
		"spring.datasource.data=classpath*:h2Data/*.sql" },*/ classes = BookmarkServiceApplication.class)
public class CompanyControllerIntegrationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void shouldGiveListOfAllCompaniesFromApiTest() {
		webClient.get().uri("/api/v1/companies").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk().expectBody()
        .consumeWith(response ->
        Assertions.assertThat(response.getResponseBody()).isNotNull());
	}

	@Test
	public void shouldGiveListOfActiveCompaniesFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies").queryParam("active", "true").build()).exchange().expectStatus().isOk().expectBody()
        .consumeWith(response ->
        Assertions.assertThat(response.getResponseBody()).isNotNull());
	}

	@Test
	public void shouldGiveListOfInactiveCompaniesFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies").queryParam("active", "false").build()).exchange().expectStatus().isOk().expectBody()
        .consumeWith(response ->
        Assertions.assertThat(response.getResponseBody()).isNotNull());
	}
	
	@Test
	public void shouldGiveCompanyDetailsFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/629fab9a-0f46-4925-8e25-4037069f7dfd").build()).exchange().expectStatus().isOk().expectBody()
        .consumeWith(response ->
        Assertions.assertThat(response.getResponseBody()).isNotNull());
	}
	
	@Test
	public void shouldGiveBadResponseOnInvalidUuidTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/01234-56789-123456-789123456-7891234-567").build()).exchange().expectStatus().isBadRequest();
	}

	@Test
	public void shouldGiveNotFoundOnUnknownUuidTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/629fab9a-0f46-0000-8e25-4037069f7dfd").build()).exchange().expectStatus().isNotFound();
	}
}
