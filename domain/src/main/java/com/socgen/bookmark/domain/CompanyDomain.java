package com.socgen.bookmark.domain;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.User;
import com.socgen.bookmark.domain.port.CompanyDomainPort;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;
import com.socgen.bookmark.jpa.port.GroupJpaPort;
import com.socgen.bookmark.jpa.port.UserJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyDomain implements CompanyDomainPort {

	private final CompanyJpaPort companyJpaPort;

	private final UserJpaPort userJpaPort;
	
	private final GroupJpaPort groupJpaPort;

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
	public CompanyData getCompanyByUrlContext(final String urlContext) {
		return companyJpaPort.getCompanyByUrlContext(urlContext);
	}

	@Override
	public User getCompanyUsers(final String comapnyUrlContext, Boolean active) {
		User users = null;
		if (null == active) {
			users = userJpaPort.getCompanyUsers(comapnyUrlContext);
		} else if (active) {
			users = userJpaPort.getCompanyActiveUsers(comapnyUrlContext);
		} else {
			users = userJpaPort.getCompanyInactiveUsers(comapnyUrlContext);
		}
		return users;
	}
	
	@Override
	public Group getCompanyGroups(final String comapnyUrlContext, Boolean active) {
		Group groups = null;
		if (null == active) {
			groups = groupJpaPort.getCompanyGroups(comapnyUrlContext);
		} else if (active) {
			groups = groupJpaPort.getCompanyActiveGroups(comapnyUrlContext);
		} else {
			groups = groupJpaPort.getCompanyInactiveGroups(comapnyUrlContext);
		}
		return groups;
	}
}
