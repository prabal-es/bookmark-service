package com.socgen.bookmark.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.socgen.bookmark.BookmarkServiceApplication;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, value = {
		"spring.datasource.data=classpath*:h2Data/*.sql" }, classes = BookmarkServiceApplication.class)
public class HelloControllerIntegrationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void helloWorldTest() {
		this.webClient.get().uri("/api/v1/hello").exchange().expectStatus().isOk().expectBody(String.class)
				.isEqualTo("hello world!!");
	}

	@Test
	public void helloUniverseTest() {
		this.webClient.get().uri(uriBuilder -> uriBuilder.path("/api/v1/hello").queryParam("test", "Universe").build())
				.exchange().expectStatus().isOk().expectBody(String.class).isEqualTo("hello Universe");
	}
}
