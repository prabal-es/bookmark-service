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
import com.socgen.bookmark.domain.model.Card.CardData;
import com.socgen.bookmark.domain.model.Card.CardType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = BookmarkServiceApplication.class)
@ActiveProfiles(profiles = "test")
public class CardControllerIntegrationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void shouldGiveListOfAllUserCardsFromApiTest() {
		webClient.get().uri("/api/v1/cards?type=CARD").accept(MediaType.APPLICATION_JSON)
				.header("company-context", "soc-gen").header("user-context", "prabal9").exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.data").isNotEmpty()
				.jsonPath("$.data", hasSize(2));
	}

	@Test
	public void shouldGiveListOfAllUserTinesFromApiTest() {
		webClient.get().uri("/api/v1/cards?type=TINY").accept(MediaType.APPLICATION_JSON)
				.header("company-context", "soc-gen").header("user-context", "prabal9").exchange().expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.data").isNotEmpty()
				.jsonPath("$.data", hasSize(3));
	}

	@Test
	public void shouldCreateNewCardForUserFromApiTest() {
		webClient.post().uri("/api/v1/cards").header("company-context", "soc-gen").header("user-context", "anshu")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(CardData.builder().type(CardType.CARD).description("This is test card").name("TEST CARD")
						.detailUrl("http://www.google.com").build())
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isCreated().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.name").isEqualTo("TEST CARD");
	}

	@Test
	public void shouldCreateNewTinyForUserFromApiTest() {
		webClient.post().uri("/api/v1/cards").header("company-context", "soc-gen").header("user-context", "anshu")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(CardData.builder().type(CardType.TINY).description("This is test card").name("TEST Tiny")
						.detailUrl("http://www.google.com").build())
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isCreated().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.name").isEqualTo("TEST Tiny");
	}

	@Test
	public void shouldCreateNewTinyWithExpireyOfOneMinsForUserFromApiTest() {
		webClient.post().uri("/api/v1/cards").header("company-context", "soc-gen").header("user-context", "anshu")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(CardData.builder().type(CardType.TINY).description("This is test card").name("TEST Tiny")
						.detailUrl("http://www.google.com").expireAt(System.currentTimeMillis() + 60000).build())
				.accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isCreated().expectHeader()
				.contentType(MediaType.APPLICATION_JSON).expectBody().jsonPath("$.name").isEqualTo("TEST Tiny");
	}

	@Test
	public void shouldGiveBadRequestFoInvalidUrlTest() {
		webClient.post().uri("/api/v1/cards").header("company-context", "soc-gen").header("user-context", "anshu")
				.contentType(MediaType.APPLICATION_JSON).bodyValue(CardData.builder().type(CardType.TINY)
						.description("This is test card").name("TEST Tiny").detailUrl("invalid url").build())
				.exchange().expectStatus().isBadRequest();
	}
}
