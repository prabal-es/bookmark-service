package com.socgen.bookmark.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;

import com.socgen.bookmark.domain.model.Group;
import com.socgen.bookmark.domain.model.Group.GroupData;
import com.socgen.bookmark.jpa.entity.CompanyEntity;
import com.socgen.bookmark.jpa.entity.GroupEntity;
import com.socgen.bookmark.jpa.port.GroupJpaPort;
import com.socgen.bookmark.jpa.repository.GroupRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupJpaAdapter implements GroupJpaPort {

	private final GroupRepository groupRepository;

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
