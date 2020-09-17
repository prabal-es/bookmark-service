package com.socgen.bookmark.rest;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.bookmark.domain.port.CardDomainPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
@Tag(name = "Tiny")
public class TinyController {

	private final CardDomainPort cardDomainPort;

	@GetMapping("/{url-context}/{tiny-code}")
	@Operation(description = "Redirect to final URL", summary = "Redirect to final URL of a given tiny code from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "308", description = "Permanent redirect to detailed url.", content = @Content),
			@ApiResponse(responseCode = "404", description = "Group not exist.", content = @Content) })
	public ResponseEntity<String> getTiny(
			@Parameter(description = "URL context id of a tiny") @PathVariable(name = "url-context") final String urlContext,
			@Parameter(description = "Tiny code of a tiny") @PathVariable(name = "tiny-code") final String tinyCode) {
		String detailUrl = cardDomainPort.getCardUrl(urlContext, tinyCode);
		System.out.println(detailUrl);
		if (null != detailUrl) {
			return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT).location(URI.create(detailUrl)).build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
