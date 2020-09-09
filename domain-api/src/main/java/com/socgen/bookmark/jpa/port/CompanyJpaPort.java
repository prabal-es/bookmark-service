package com.socgen.bookmark.jpa.port;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;

public interface CompanyJpaPort {

	Company getCompanies();
	
	Company getActiveCompanies();
	
	Company getInactiveCompanies();
	
	CompanyData getCompanyByUuid(String uuid);
	
}
