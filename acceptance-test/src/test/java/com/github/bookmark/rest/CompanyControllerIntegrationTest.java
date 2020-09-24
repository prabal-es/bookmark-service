package com.github.bookmark.rest;

import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.bookmark.BookmarkServiceApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BookmarkServiceApplication.class)
@ActiveProfiles(profiles = "test")
public class CompanyControllerIntegrationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void shouldGiveListOfAllCompaniesFromApiTest() {
		webClient.get().uri("/api/v1/companies").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.data").isNotEmpty()
				.jsonPath("$.data", hasSize(2));
	}

	@Test
	public void shouldGiveListOfActiveCompaniesFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies").queryParam("active", "true").build())
				.exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.data").isNotEmpty().jsonPath("$.data", hasSize(2));
	}

	@Test
	public void shouldGiveListOfInactiveCompaniesFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies").queryParam("active", "false").build())
				.exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.data").isNotEmpty().jsonPath("$.data", hasSize(0));
	}

	@Test
	public void shouldGiveCompanyDetailsFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/google").build()).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$").isNotEmpty().jsonPath("$.name").isEqualTo("Google, LLC");
	}

	@Test
	public void shouldGiveNotFoundResponseOnInvalidUrlContextTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/xyz").build()).exchange()
				.expectStatus().isNotFound();
	}


	@Test
	public void shouldGiveAllUsersForGivenUrlContextTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/google/users").build()).exchange()
		.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
		.jsonPath("$.data").isNotEmpty().jsonPath("$.data", hasSize(3));
	}
	
	@Test
	public void shouldGiveAllActiveUsersForGivenUrlContextTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/google/users").queryParam("active", "true").build()).exchange()
		.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
		.jsonPath("$.data").isNotEmpty().jsonPath("$.data", hasSize(3));
	}
	
	@Test
	public void shouldGiveAllInactiveUsersForGivenUrlContextTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/google/users").queryParam("active", "false").build()).exchange()
		.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
		.jsonPath("$.data",hasSize(0));
	}
	
	@Test
	public void shouldGiveAllGroupsForGivenUrlContextTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/google/groups").build()).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.data").isNotEmpty().jsonPath("$.data", hasSize(3));
	}
	
	@Test
	public void shouldGiveActiveGroupsForGivenUrlContextTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/google/groups").queryParam("active", "true").build()).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.data").isNotEmpty().jsonPath("$.data", hasSize(3));
	}
	
	@Test
	public void shouldGiveInctiveGroupsForGivenUrlContextTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/companies/google/groups").queryParam("active", "false").build()).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.data", hasSize(3));
	}
}
