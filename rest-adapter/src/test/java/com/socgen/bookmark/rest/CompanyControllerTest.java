package com.socgen.bookmark.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.domain.port.CompanyDomainPort;

@WebMvcTest(value = CompanyController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CompanyControllerTest {

	private final MockMvc mockMvc;

	@MockBean
	private CompanyDomainPort companyDomainPort;

	@Autowired
	public CompanyControllerTest(final MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	@DisplayName("shoud give list of all companies from API")
	public void shouldGiveListOfAllCompaniesFromApi() throws Exception {
		Mockito.when(companyDomainPort.getCompanies(null)).thenReturn(getCompanies(null));
		mockMvc.perform(get("/api/v1/companies"))/* .andDo(print()) */.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.length()").value(2)).andExpect(jsonPath("$.data[0].uuid").value("629fab9a-0f46-4925-8e25-4037069f7dfd"));

	}

	@Test
	@DisplayName("shoud give list of all active companies from API")
	public void shouldGiveListOfActiveCompaniesFromApi() throws Exception {
		Mockito.when(companyDomainPort.getCompanies(true)).thenReturn(getCompanies(true));
		mockMvc.perform(get("/api/v1/companies").queryParam("active", "true")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.length()").value(1)).andExpect(jsonPath("$.data[0].uuid").value("629fab9a-0f46-4925-8e25-4037069f7dfd"));
	}

	@Test
	@DisplayName("shoud give list of all inactive companies from API")
	public void shouldGiveListOfInactiveCompaniesFromApi() throws Exception {
		Mockito.when(companyDomainPort.getCompanies(false)).thenReturn(getCompanies(false));
		mockMvc.perform(get("/api/v1/companies").queryParam("active", "false")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.data.length()").value(1)).andExpect(jsonPath("$.data[0].uuid").value("629fab9a-0f46-4925-6666-4037069f7dfd"));
	}

	@Test
	@DisplayName("shoud give companies details from API")
	public void shouldGiveCompanyDetailsFromApi() throws Exception {
		Mockito.when(companyDomainPort.getCompanyByUuid("629fab9a-0f46-4925-8e25-4037069f7dfd")).thenReturn(getSocGenData());
		mockMvc.perform(get("/api/v1/companies/629fab9a-0f46-4925-8e25-4037069f7dfd")).andExpect(status().isOk())
				.andExpect(jsonPath("$.uuid").value("629fab9a-0f46-4925-8e25-4037069f7dfd"));
	}

	@Test
	@DisplayName("shoud give bad response on invalid UUID")
	public void shouldGiveBadResponseOnInvalidUuid() throws Exception {
		mockMvc.perform(get("/api/v1/companies/01234-56789-123456-789123456-7891234-567"))
				.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("shoud give not found response on unknown UUID")
	public void shouldGiveNotFoundOnUnknownUuid() throws Exception {
		Mockito.when(companyDomainPort.getCompanyByUuid("629fab9a-0f46-4925-9999-4037069f7dfd")).thenReturn(null);
		mockMvc.perform(get("/api/v1/companies/629fab9a-0f46-4925-9999-4037069f7dfd")).andExpect(status().isNotFound());
	}

	private Company getCompanies(final Boolean active) {
		List<CompanyData> companyData = new ArrayList<>();
		if (null == active || active) {
			companyData.add(getSocGenData());
		}
		if (null == active || !active) {
			companyData.add(getAdpData());
		}
		return Company.builder().data(companyData).build();
	}

	private CompanyData getSocGenData() {
		return CompanyData.builder().uuid("629fab9a-0f46-4925-8e25-4037069f7dfd").name("SocGen").description("SocGen Bank").active(true).build();
	}

	private CompanyData getAdpData() {
		return CompanyData.builder().uuid("629fab9a-0f46-4925-6666-4037069f7dfd").name("ADP").description("ADP LLC").active(false).build();
	}

}
