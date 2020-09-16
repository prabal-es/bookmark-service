package com.socgen.bookmark.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.socgen.bookmark.domain.model.User.UserData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
public class Group {
	
	public enum GroupType {
		SELF, ALL, OTHER
	};
	
	private List<GroupData> data;

	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Getter
	@JsonInclude(value = Include.NON_NULL)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class GroupData {

		private String uuid;

		private String name;

		@Setter
		private String urlContext;
		
		private String description;
		
		private String img;

		private Boolean active;
		
		private List<UserData> adminUsers;
	}
}
