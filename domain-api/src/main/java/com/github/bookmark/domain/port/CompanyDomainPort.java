package com.github.bookmark.domain.port;

import com.github.bookmark.domain.model.Company;
import com.github.bookmark.domain.model.Company.CompanyData;
import com.github.bookmark.domain.model.Group;
import com.github.bookmark.domain.model.User;

public interface CompanyDomainPort {

	Company getCompanies(Boolean active);
	
	CompanyData getCompanyByUrlContext(String urlContext);
	
	User getCompanyUsers(String comapnyUrlContext, Boolean active);
	
	Group getCompanyGroups(String comapnyUrlContext, Boolean active);
}
