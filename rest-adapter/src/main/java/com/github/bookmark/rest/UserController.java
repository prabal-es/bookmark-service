package com.github.bookmark.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.bookmark.domain.model.User;
import com.github.bookmark.domain.port.UserDomainPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/users")
@Tag(name = "User")
public class UserController {

	private final UserDomainPort userDomainPort;

	@GetMapping
	@Operation(description = "Get list of users", summary = "Retrive all the users from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all the users.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))) })
	public User getUsers() {
		return userDomainPort.getUsers();
	}

}
