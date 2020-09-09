package com.socgen.bookmark.domain.port;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;

public interface CompanyDomainPort {

	Company getCompanies(Boolean active);
	
	CompanyData getCompanyByUuid(String uuid);
}
