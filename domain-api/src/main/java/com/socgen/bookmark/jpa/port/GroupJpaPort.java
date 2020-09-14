package com.socgen.bookmark.jpa.port;

import com.socgen.bookmark.domain.model.Group;

public interface GroupJpaPort {

	Group getCompanyGroups(String comapnyUrlContext);
	
	Group getCompanyActiveGroups(String comapnyUrlContext);
	
	Group getCompanyInactiveGroups(String comapnyUrlContext);

}
