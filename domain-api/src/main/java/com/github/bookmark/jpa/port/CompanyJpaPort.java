package com.github.bookmark.jpa.port;

import com.github.bookmark.domain.model.Company;
import com.github.bookmark.domain.model.Company.CompanyData;

public interface CompanyJpaPort {

	Company getCompanies();
	
	Company getActiveCompanies();
	
	Company getInactiveCompanies();
	
	CompanyData getCompanyByUrlContext(String urlContext);
	
}
