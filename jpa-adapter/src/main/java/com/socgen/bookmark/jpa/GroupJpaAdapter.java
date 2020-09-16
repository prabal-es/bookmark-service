package com.socgen.bookmark.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.Group.GroupData;
import com.socgen.bookmark.jpa.entity.CompanyEntity;
import com.socgen.bookmark.jpa.entity.GroupEntity;
import com.socgen.bookmark.jpa.entity.UserEntity;
import com.socgen.bookmark.jpa.port.GroupJpaPort;
import com.socgen.bookmark.jpa.repository.GroupRepository;
import com.socgen.bookmark.jpa.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupJpaAdapter implements GroupJpaPort {

	private final GroupRepository groupRepository;

	private final UserRepository userRepository;

	@Override
	public Group getCompanyGroups(String comapnyUrlContext) {
		return mapGroups(groupRepository.findAll(Example.of(
				GroupEntity.builder().company(CompanyEntity.builder().urlContext(comapnyUrlContext).build()).build())));
	}

	@Override
	public Group getCompanyActiveGroups(String comapnyUrlContext) {
		return mapGroups(groupRepository.findAll(Example.of(GroupEntity.builder()
				.company(CompanyEntity.builder().urlContext(comapnyUrlContext).build()).active(Boolean.TRUE).build())));
	}

	@Override
	public Group getCompanyInactiveGroups(String comapnyUrlContext) {
		return mapGroups(groupRepository.findAll(
				Example.of(GroupEntity.builder().company(CompanyEntity.builder().urlContext(comapnyUrlContext).build())
						.active(Boolean.FALSE).build())));
	}

	@Override
	public Group getUserGroups(String companyContext, String userContext) {
		return mapGroups(groupRepository.findAllAdminUserGroups(companyContext, userContext));
	}

	@Override
	public Group getOtherGroups(String companyContext, String userContext) {
		return mapGroups(groupRepository.findAllOtherUserGroups(companyContext, userContext));
	}

	@Override
	public GroupData createGroup(String companyContext, String userContext, GroupData groupData) {
		GroupData data = null;
		String groupUrlContext = groupData.getUrlContext();
		for (int i = 0; getGroupEntityByUrlContext(groupUrlContext); i++) {
			groupUrlContext = groupData.getUrlContext().concat("-" + i);
		}
		Optional<UserEntity> userOptional = userRepository
				.findOne(Example.of(UserEntity.builder().urlContext(userContext).build()));
		if (userOptional.isPresent()) {
			UserEntity adminUser = userOptional.get();

			GroupEntity entity = GroupEntity.builder().company(adminUser.getCompany())
					.adminUsers(Collections.singleton(adminUser)).active(true).description(groupData.getDescription())
					.name(groupData.getName()).img(groupData.getImg()).urlContext(groupUrlContext).build();
			return mapGroupData(groupRepository.save(entity));
		}
		return data;
	}

	private boolean getGroupEntityByUrlContext(final String groupContext) {
		return groupRepository.findOne(Example.of(GroupEntity.builder().urlContext(groupContext).build())).isPresent();
	}

	private Group mapGroups(final List<GroupEntity> groupEntities) {
		var groups = new ArrayList<GroupData>();
		groupEntities.forEach(groupEntity -> {
			groups.add(mapGroupData(groupEntity));
		});
		return Group.builder().data(groups).build();
	}

	private GroupData mapGroupData(final GroupEntity groupEntity) {
		return GroupData.builder().uuid(groupEntity.getUuid().toString()).name(groupEntity.getName())
				.urlContext(groupEntity.getUrlContext()).img(groupEntity.getImg())
				.description(groupEntity.getDescription()).active(groupEntity.getActive()).build();
	}

}
