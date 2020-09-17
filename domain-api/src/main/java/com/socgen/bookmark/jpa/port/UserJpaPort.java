package com.socgen.bookmark.jpa.port;

import com.socgen.bookmark.domain.model.User;

public interface UserJpaPort {

	User getUsers();
	
	User getCompanyUsers(String comapnyUrlContext);
	
	User getCompanyActiveUsers(String comapnyUrlContext);
	
	User getCompanyInactiveUsers(String comapnyUrlContext);

}
