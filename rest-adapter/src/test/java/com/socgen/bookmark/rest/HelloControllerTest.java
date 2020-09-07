package com.socgen.bookmark.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = HelloController.class)
@AutoConfigureMockMvc(addFilters = false)
public class HelloControllerTest {

	private MockMvc mockMvc;

	@Autowired
	public HelloControllerTest(final MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	@DisplayName("shoud give greatings from hello API")
	public void shouldGiveGreatingsFromHelloApi() throws Exception {
		mockMvc.perform(get("/api/v1/hello"))/*.andDo(print())*/.andExpect(status().isOk())
				.andExpect(content().string("hello world!!"));
	}
	
	@Test
	@DisplayName("shoud give greatings to universe from hello API")
	public void shouldGiveGreatingsToUniverseFromHelloApi() throws Exception {
		mockMvc.perform(get("/api/v1/hello").queryParam("test", "universe"))
				/* .andDo(print()) */.andExpect(status().isOk())
				.andExpect(content().string("hello universe"));
	}
}
