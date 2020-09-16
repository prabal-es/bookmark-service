package com.socgen.bookmark.jpa.port;

import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.Group.GroupData;

public interface GroupJpaPort {

	Group getCompanyGroups(String comapnyUrlContext);
	
	Group getCompanyActiveGroups(String comapnyUrlContext);
	
	Group getCompanyInactiveGroups(String comapnyUrlContext);
	
	Group getUserGroups(String companyContext, String userContext);
	
	Group getOtherGroups(String companyContext, String userContext);

	GroupData createGroup(String companyContext, String userContext, GroupData groupData);
}
