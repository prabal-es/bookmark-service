package com.socgen.bookmark.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.port.CompanyDomainPort;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

//@EnableOAuth2Sso
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

	private final CompanyDomainPort companyDomainPort;
	
	@GetMapping
	@Operation(description = "This is description", summary = "This is summary", tags = { "testTag" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "The request has succeeded or (your message)"),
			@ApiResponse(responseCode = "401", description = "The request requires user authentication or (your message)"),
			@ApiResponse(responseCode = "403", description = "Accessing the resource you were trying to reach is forbidden or (your message)"),
			@ApiResponse(responseCode = "404", description = "The server has not found anything matching the Request-URI or (your message)") })
	public Company getCompanies() {
		return companyDomainPort.getCompanies();
	}
	

}
