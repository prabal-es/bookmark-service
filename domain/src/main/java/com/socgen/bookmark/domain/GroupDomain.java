package com.socgen.bookmark.domain;

import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.Group.GroupData;
import com.socgen.bookmark.domain.model.Group.GroupType;
import com.socgen.bookmark.domain.port.GroupDomainPort;
import com.socgen.bookmark.jpa.port.GroupJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupDomain implements GroupDomainPort {

	private final GroupJpaPort groupJpaPort;

	@Override
	public Group getGroups(String companyContext, String userContext, GroupType type) {
		if (type == GroupType.ALL) {
			return groupJpaPort.getCompanyGroups(companyContext);
		} else if (type == GroupType.SELF) {
			return groupJpaPort.getUserGroups(companyContext, userContext);
		} else {
			return groupJpaPort.getOtherGroups(companyContext, userContext);
		}
	}

	@Override
	public GroupData createGroup(String companyContext, String userContext, GroupData groupData) {
		groupData.setUrlContext(groupData.getName().replaceAll(" ", "-"));
		return groupJpaPort.createGroup(companyContext, userContext, groupData);
	}

}
