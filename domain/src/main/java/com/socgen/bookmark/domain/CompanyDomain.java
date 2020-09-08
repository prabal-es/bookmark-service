package com.socgen.bookmark.domain;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.port.CompanyDomainPort;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyDomain implements CompanyDomainPort {

	private final CompanyJpaPort companyJpaPort;

	@Override
	public Company getCompanies() {
		return companyJpaPort.getCompanies();
	}

}
