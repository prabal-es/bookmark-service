package com.github.bookmark.domain;

import java.util.List;

import com.github.bookmark.domain.model.Group;
import com.github.bookmark.domain.model.Group.GroupData;
import com.github.bookmark.domain.model.Group.GroupType;
import com.github.bookmark.domain.port.GroupDomainPort;
import com.github.bookmark.jpa.port.GroupJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupDomain implements GroupDomainPort {

	private final GroupJpaPort groupJpaPort;

	@Override
	public Group getGroups(final String companyContext, final String userContext, final GroupType type) {
		if (type == GroupType.ALL) {
			return groupJpaPort.getCompanyGroups(companyContext);
		} else if (type == GroupType.SELF) {
			return groupJpaPort.getUserGroups(companyContext, userContext);
		} else {
			return groupJpaPort.getOtherGroups(companyContext, userContext);
		}
	}

	@Override
	public GroupData createGroup(final String companyContext, final String userContext, final GroupData groupData) {
		groupData.setUrlContext(groupData.getName().replaceAll(" ", "-"));
		return groupJpaPort.createGroup(companyContext, userContext, groupData);
	}

	@Override
	public GroupData getGroupByUrlContext(final String urlContext) {
		return groupJpaPort.getGroupByUrlContext(urlContext);
	}

	@Override
	public GroupData updateGroupAdmins(final String urlContext, final List<String> userIds) {
		return groupJpaPort.updateGroupAdmins(urlContext, userIds);
	}

	@Override
	public GroupData updateGroupCards(final String urlContext, final List<String> cardIds) {
		return groupJpaPort.updateGroupCards(urlContext, cardIds);
	}

}
