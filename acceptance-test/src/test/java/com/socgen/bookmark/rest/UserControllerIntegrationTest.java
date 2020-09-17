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
public class UserControllerIntegrationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void shouldGiveListOfAllUsersForACompanyFromApiTest() {
		webClient.get().uri("/api/v1/users").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.data").isNotEmpty()
				.jsonPath("$.data", hasSize(3));
	}

}
