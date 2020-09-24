package com.github.bookmark.jpa.port;

import com.github.bookmark.domain.model.User;

public interface UserJpaPort {

	User getUsers();
	
	User getCompanyUsers(String comapnyUrlContext);
	
	User getCompanyActiveUsers(String comapnyUrlContext);
	
	User getCompanyInactiveUsers(String comapnyUrlContext);

}
