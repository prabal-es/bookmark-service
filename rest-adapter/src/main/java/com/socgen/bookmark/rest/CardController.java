package com.socgen.bookmark.rest;

import org.apache.commons.validator.routines.UrlValidator;
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

import com.socgen.bookmark.domain.model.Card;
import com.socgen.bookmark.domain.model.Card.CardData;
import com.socgen.bookmark.domain.model.Card.CardType;
import com.socgen.bookmark.domain.port.CardDomainPort;

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
@RequestMapping("/api/v1/cards")
@Tag(name = "Card")
public class CardController {

	private final CardDomainPort cardDomainPort;

	private final UrlValidator urlValidator;

	@GetMapping
	@Operation(description = "Get list of cards of a given user", summary = "Retrive all the cards of a given user from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all the tinies of a given user.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Card.class))) })
	public Card getCards(
			@Parameter(description = "Company URL context") @RequestHeader(name = "company-context") final String companyContext,
			@Parameter(description = "User URL context") @RequestHeader(name = "user-context") final String userContext,
			@Parameter(description = "Type of card") @RequestParam(name = "type", defaultValue = "CARD", required = true) final CardType type) {
		return cardDomainPort.getCards(companyContext, userContext, type);
	}

	@PostMapping
	@Operation(description = "Create card for the given user", summary = "Create card for the given user in bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Card created successfully for the given user.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CardData.class))),
			@ApiResponse(responseCode = "400", description = "URL is not valid", content = @Content) })
	public ResponseEntity<CardData> createCard(
			@Parameter(description = "Company URL context") @RequestHeader(name = "company-context") final String companyContext,
			@Parameter(description = "User URL context") @RequestHeader(name = "user-context") final String userContext,
			@RequestBody CardData cardData) {
		if (cardData.getType() == CardType.TINY && !urlValidator.isValid(cardData.getDetailUrl())
				|| cardData.getDetailUrl().length() > 2083) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(cardDomainPort.createCard(companyContext, userContext, cardData));
		}
	}
}
