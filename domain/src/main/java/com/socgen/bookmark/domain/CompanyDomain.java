package com.socgen.bookmark.domain;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.domain.model.User;
import com.socgen.bookmark.domain.port.CompanyDomainPort;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;
import com.socgen.bookmark.jpa.port.UserJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyDomain implements CompanyDomainPort {

	private final CompanyJpaPort companyJpaPort;

	private final UserJpaPort userJpaPort;

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

	@Override
	public User getCompanyUsers(final String comapnyUuid, Boolean active) {
		User users = null;
		if (null == active) {
			users = userJpaPort.getCompanyUsers(comapnyUuid);
		} else if (active) {
			users = userJpaPort.getCompanyActiveUsers(comapnyUuid);
		} else {
			users = userJpaPort.getCompanyInactiveUsers(comapnyUuid);
		}
		return users;
	}
}
