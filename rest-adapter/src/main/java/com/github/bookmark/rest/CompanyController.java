package com.github.bookmark.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.bookmark.domain.model.Company;
import com.github.bookmark.domain.model.Company.CompanyData;
import com.github.bookmark.domain.model.Group;
import com.github.bookmark.domain.model.User;
import com.github.bookmark.domain.port.CompanyDomainPort;

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

	@GetMapping("/{url-context}")
	@Operation(description = "Get details of company", summary = "Retrive all the details of a company from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Company details.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CompanyData.class))),
			@ApiResponse(responseCode = "404", description = "Company not exist.", content = @Content) })
	public ResponseEntity<CompanyData> getCompany(
			@Parameter(description = "URL context id of the company") @PathVariable(name = "url-context", required = false) final String urlContext) {

		CompanyData companyData = companyDomainPort.getCompanyByUrlContext(urlContext);

		if (null == companyData) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(companyData);
	}

	@GetMapping("/{url-context}/users")
	@Operation(description = "Get list of company users", summary = "Retrive all the list of company users from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of company users.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = User.class))) })
	public ResponseEntity<User> getCompanyUsers(
			@Parameter(description = "URL context id of the company") @PathVariable(name = "url-context", required = false) final String urlContext,
			@Parameter(description = "State of the company users.") @RequestParam(name = "active", required = false) final Boolean active) {
		return ResponseEntity.ok()
				.body(companyDomainPort.getCompanyUsers(urlContext, null == active ? active : Boolean.valueOf(active)));
	}

	@GetMapping("/{url-context}/groups")
	@Operation(description = "Get list of company groups", summary = "Retrive all the list of company groups from bookmark service.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of company groups.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Group.class))) })
	public ResponseEntity<Group> getCompanyGroups(
			@Parameter(description = "URL context id of the company") @PathVariable(name = "url-context", required = false) final String urlContext,
			@Parameter(description = "State of the company group.") @RequestParam(name = "active", required = false) final Boolean active) {

		return ResponseEntity.ok().body(
				companyDomainPort.getCompanyGroups(urlContext, null == active ? active : Boolean.valueOf(active)));
	}
}
