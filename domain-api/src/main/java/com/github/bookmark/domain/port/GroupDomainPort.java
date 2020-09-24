package com.github.bookmark.domain.port;

import java.util.List;

import com.github.bookmark.domain.model.Group;
import com.github.bookmark.domain.model.Group.GroupData;
import com.github.bookmark.domain.model.Group.GroupType;

public interface GroupDomainPort {

	Group getGroups(String companyContext, String userContext, GroupType type);

	GroupData createGroup(String companyContext, String userContext, GroupData groupData);

	GroupData getGroupByUrlContext(String urlContext);

	GroupData updateGroupAdmins(String urlContext, List<String> userIds);

	GroupData updateGroupCards(String urlContext, List<String> cardIds);
}
