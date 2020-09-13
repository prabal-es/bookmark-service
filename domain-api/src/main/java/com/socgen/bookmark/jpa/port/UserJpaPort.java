package com.socgen.bookmark.jpa.port;

import com.socgen.bookmark.domain.model.User;

public interface UserJpaPort {

	User getCompanyUsers(String comapnyUuid);
	
	User getCompanyActiveUsers(String comapnyUuid);
	
	User getCompanyInactiveUsers(String comapnyUuid);

}
