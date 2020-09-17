package com.socgen.bookmark.rest;

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
public class TinyControllerIntegrationTest {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void shouldRedirectTotheFinalURL() {
		webClient.get().uri("/tiny/811ec2f0").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isPermanentRedirect();
	}
	
	@Test
	public void shouldGiveNotFountForInvalidCode() {
		webClient.get().uri("/tiny/xyz-code").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isNotFound();
	}

}
