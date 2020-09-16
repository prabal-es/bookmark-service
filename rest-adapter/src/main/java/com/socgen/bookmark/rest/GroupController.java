package com.socgen.bookmark.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.Group.GroupData;
import com.socgen.bookmark.domain.model.Group.GroupType;
import com.socgen.bookmark.domain.port.GroupDomainPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/groups")
@Tag(name = "Group")
public class GroupController {

	private final GroupDomainPort groupDomainPort;

	@GetMapping
	@Operation(description = "Get list of group", summary = "Retrive all the group from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all the group.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Group.class))) })
	public Group getGroups(
			@Parameter(description = "Company URL context") @RequestHeader(name = "company-context") final String companyContext,
			@Parameter(description = "User URL context") @RequestHeader(name = "user-context") final String userContext,
			@Parameter(description = "Type of group") @RequestParam(name = "type", defaultValue = "SELF", required = true) final GroupType type) {
		return groupDomainPort.getGroups(companyContext, userContext, type);
	}
	
	@PostMapping
	@Operation(description = "Create group for the given user", summary = "Create group for the given user in bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Card created successfully for the given user.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = GroupData.class))),
			@ApiResponse(responseCode = "400", description = "URL is not valid", content = @Content) })
	public ResponseEntity<GroupData> createCard(
			@Parameter(description = "Company URL context") @RequestHeader(name = "company-context") final String companyContext,
			@Parameter(description = "User URL context") @RequestHeader(name = "user-context") final String userContext,
			@RequestBody GroupData groupData) {
		return ResponseEntity.status(201).body(groupDomainPort.createGroup(companyContext, userContext, groupData));
	}
}
