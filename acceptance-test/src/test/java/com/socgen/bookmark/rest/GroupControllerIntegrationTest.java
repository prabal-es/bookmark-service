package com.socgen.bookmark.rest;

import static org.hamcrest.Matchers.hasSize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.socgen.bookmark.BookmarkServiceApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BookmarkServiceApplication.class)
@ActiveProfiles(profiles = "test")
public class GroupControllerIntegrationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void shouldGiveListOfAllGroupsForACompanyFromApiTest() {
		webClient.get().uri("/api/v1/groups?type=ALL").header("company-context", "soc-gen")
				.header("user-context", "prabal9").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.data").isNotEmpty()
				.jsonPath("$.data", hasSize(3));
	}

	@Test
	public void shouldGiveListOfSelfGroupsFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/groups").queryParam("type", "SELF").build())
				.header("company-context", "soc-gen").header("user-context", "prabal9").exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.data").isNotEmpty()
				.jsonPath("$.data", hasSize(2));
	}

	@Test
	public void shouldGiveListOfOtherGroupsFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/groups").queryParam("type", "OTHER").build()).header("company-context", "soc-gen").header("user-context", "prabal9")
				.exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody()
				.jsonPath("$.data").isEmpty();
	}
	
	@Test
	public void shouldGiveBadRequestForCompanyContextMissingFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/groups").queryParam("type", "OTHER").build())
				.exchange().expectStatus().isBadRequest();
	}

	@Test
	public void shouldGiveGroupDetailsFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/groups/engineering-stream").build()).exchange()
				.expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$")
				.isNotEmpty().jsonPath("$.name").isEqualTo("Engineering Stream");
	}
	
	@Test
	public void shouldGiveBadRequestFromApiTest() {
		webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/groups/unknown").build()).exchange()
				.expectStatus().isNotFound();
	}

}
