package com.socgen.bookmark.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class Group {
	
	private List<GroupData> data;

	@AllArgsConstructor
	@Builder
	@Getter
	@JsonInclude(value = Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class GroupData {

		private String uuid;

		private String name;

		private String urlContext;
		
		private String description;
		
		private String img;

		private Boolean active;
	}
}
