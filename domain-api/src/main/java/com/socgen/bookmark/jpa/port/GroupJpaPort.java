package com.socgen.bookmark.jpa.port;

import com.socgen.bookmark.domain.model.Group;

public interface GroupJpaPort {

	Group getCompanyGroups(String comapnyUuid);
	
	Group getCompanyActiveGroups(String comapnyUuid);
	
	Group getCompanyInactiveGroups(String comapnyUuid);

}
