package com.socgen.bookmark.domain;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.domain.port.CompanyDomainPort;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyDomain implements CompanyDomainPort {

	private final CompanyJpaPort companyJpaPort;

	@Override
	public Company getCompanies(final Boolean active) {
		Company companies = null;
		if (null == active) {
			companies = companyJpaPort.getCompanies();
		} else if (active) {
			companies = companyJpaPort.getActiveCompanies();
		} else {
			companies = companyJpaPort.getInactiveCompanies();
		}
		return companies;
	}

	@Override
	public CompanyData getCompanyByUuid(final String uuid) {
		return companyJpaPort.getCompanyByUuid(uuid);
	}
}
