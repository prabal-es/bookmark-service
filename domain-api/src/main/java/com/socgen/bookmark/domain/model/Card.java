package com.socgen.bookmark.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class Card {
	public enum CardType {
		CARD, TINY
	};
	
	private List<CardData> data;

	@AllArgsConstructor
	@Builder
	@Getter
	@JsonInclude(value = Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class CardData {

		private String uuid;

		private String name;
		
		private String description;

		@Setter
		private String tinyUrl;
		
		private String detailUrl;
		
		private String img;
		
		private CardType type;
		
		@Setter
		private Long createdAt;
		
		@Setter
		private Long expireAt;
		
		private String companyContext;
		
		private String userContext;
		
		private Boolean active;
	}
}
