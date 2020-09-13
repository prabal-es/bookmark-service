package com.socgen.bookmark.domain.port;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.User;

public interface CompanyDomainPort {

	Company getCompanies(Boolean active);
	
	CompanyData getCompanyByUuid(String uuid);
	
	User getCompanyUsers(String comapnyUuid, Boolean active);
	
	Group getCompanyGroups(String comapnyUuid, Boolean active);
}
