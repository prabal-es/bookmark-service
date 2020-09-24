package com.github.bookmark.jpa.port;

import java.util.List;

import com.github.bookmark.domain.model.Group;
import com.github.bookmark.domain.model.Group.GroupData;

public interface GroupJpaPort {

	Group getCompanyGroups(String comapnyUrlContext);
	
	Group getCompanyActiveGroups(String comapnyUrlContext);
	
	Group getCompanyInactiveGroups(String comapnyUrlContext);
	
	Group getUserGroups(String companyContext, String userContext);
	
	Group getOtherGroups(String companyContext, String userContext);

	GroupData createGroup(String companyContext, String userContext, GroupData groupData);

	GroupData getGroupByUrlContext(String urlContext);

	GroupData updateGroupAdmins(String urlContext, List<String> userIds);

	GroupData updateGroupCards(String urlContext, List<String> cardIds);
}
