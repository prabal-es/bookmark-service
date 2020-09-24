package com.github.bookmark.domain.model;

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
public class User {
	public enum Role {
		ADMIN, USER
	};
	private List<UserData> data;

	@AllArgsConstructor
	@Builder
	@Getter
	@JsonInclude(value = Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class UserData {

		private String uuid;

		private String name;

		private String urlContext;
		
		private Role role;
		
		private String img;
		
		private String url;

		private Boolean active;
		
		private String companyUrlContext;
	}
}
