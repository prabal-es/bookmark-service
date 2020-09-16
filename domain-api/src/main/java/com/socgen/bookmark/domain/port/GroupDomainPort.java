package com.socgen.bookmark.domain.port;

import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.Group.GroupData;
import com.socgen.bookmark.domain.model.Group.GroupType;

public interface GroupDomainPort {

	Group getGroups(String companyContext, String userContext, GroupType type);

	GroupData createGroup(String companyContext, String userContext, GroupData groupData);
}
