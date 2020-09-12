package com.socgen.bookmark.rest;

import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.domain.port.CompanyDomainPort;

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
@RequestMapping("/api/v1/companies")
@Tag(name = "Company")
public class CompanyController {

	private final CompanyDomainPort companyDomainPort;

	@GetMapping
	@Operation(description = "Get list of companies", summary = "Retrive all the companies from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of all the companies.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Company.class))) })
	public Company getCompanies(
			@Parameter(description = "State of the company.") @RequestParam(name = "active", required = false) final Boolean active) {
		return companyDomainPort.getCompanies(null == active ? active : Boolean.valueOf(active));
	}

	@GetMapping("/{uuid}")
	@Operation(description = "Get details of company", summary = "Retrive all the details of company from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Company details.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CompanyData.class))),
			@ApiResponse(responseCode = "400", description = "Invalid universal unique id.", content = @Content),
			@ApiResponse(responseCode = "404", description = "Company not exist.", content = @Content) })
	public ResponseEntity<CompanyData> getCompany(
			@Parameter(description = "Universal unique id of the company") @PathVariable(name = "uuid", required = false) final String uuid) {
		
		try {
			UUID.fromString(uuid);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}

		CompanyData companyData = companyDomainPort.getCompanyByUuid(uuid);
		
		if (null == companyData) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(companyData);
	}
}
