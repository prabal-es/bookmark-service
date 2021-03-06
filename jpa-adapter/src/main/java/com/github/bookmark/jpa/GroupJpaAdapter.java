package com.github.bookmark.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;

import com.github.bookmark.domain.model.Card.CardData;
import com.github.bookmark.domain.model.Group;
import com.github.bookmark.domain.model.Group.GroupData;
import com.github.bookmark.domain.model.User.UserData;
import com.github.bookmark.jpa.entity.CardEntity;
import com.github.bookmark.jpa.entity.CompanyEntity;
import com.github.bookmark.jpa.entity.GroupEntity;
import com.github.bookmark.jpa.entity.UserEntity;
import com.github.bookmark.jpa.port.GroupJpaPort;
import com.github.bookmark.jpa.repository.CardRepository;
import com.github.bookmark.jpa.repository.GroupRepository;
import com.github.bookmark.jpa.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GroupJpaAdapter implements GroupJpaPort {

	private final GroupRepository groupRepository;

	private final UserRepository userRepository;

	private final CardRepository cardRepository;

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

	@Override
	public GroupData getGroupByUrlContext(String urlContext) {
		GroupData data = null;
		Optional<GroupEntity> groupEntityOptional = groupRepository
				.findOne(Example.of(GroupEntity.builder().urlContext(urlContext).build()));
		if (groupEntityOptional.isPresent()) {
			data = mapGroupData(groupEntityOptional.get());
		}
		return data;
	}

	@Override
	public GroupData updateGroupAdmins(String urlContext, List<String> userIds) {
		GroupData data = null;
		Optional<GroupEntity> groupEntityOptional = groupRepository
				.findOne(Example.of(GroupEntity.builder().urlContext(urlContext).build()));
		if (groupEntityOptional.isPresent()) {
			GroupEntity groupEntity = groupEntityOptional.get();
			groupEntity.getAdminUsers().clear();
			userIds.forEach(userId -> {
				groupEntity.getAdminUsers().add(userRepository.getOne(UUID.fromString(userId)));
			});
			groupRepository.save(groupEntity);
			data = mapGroupData(groupEntity);
		}
		return data;
	}

	@Override
	public GroupData updateGroupCards(String urlContext, List<String> cardIds) {
		GroupData data = null;
		Optional<GroupEntity> groupEntityOptional = groupRepository
				.findOne(Example.of(GroupEntity.builder().urlContext(urlContext).build()));
		if (groupEntityOptional.isPresent()) {
			GroupEntity groupEntity = groupEntityOptional.get();
			groupEntity.getGroupCards().clear();
			cardIds.forEach(cardId -> {
				CardEntity cardEntity = cardRepository.getOne(UUID.fromString(cardId));
				cardEntity.setExpireAt(null);
				cardRepository.save(cardEntity);
				groupEntity.getGroupCards().add(cardEntity);
			});
			groupRepository.save(groupEntity);
			data = mapGroupData(groupEntity);
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
		List<UserData> adminUsers = new ArrayList<>();
		groupEntity.getAdminUsers().forEach(adminUserEntity -> {
			adminUsers.add(UserData.builder().uuid(adminUserEntity.getUuid().toString()).name(adminUserEntity.getName())
					.urlContext(adminUserEntity.getUrlContext()).role(adminUserEntity.getRole())
					.img(adminUserEntity.getImg()).url(adminUserEntity.getUrl()).active(adminUserEntity.getActive())
					.companyUrlContext(adminUserEntity.getCompany().getUrlContext()).build());
		});

		List<CardData> cardData = new ArrayList<>();
		if (null != groupEntity.getGroupCards()) {
			groupEntity.getGroupCards().forEach(cardEntity -> {
				cardData.add(CardData.builder().uuid(cardEntity.getUuid().toString()).name(cardEntity.getName())
						.description(cardEntity.getDescription()).tinyUrl(cardEntity.getTinyUrl())
						.detailUrl(cardEntity.getDetailUrl()).img(cardEntity.getImg()).type(cardEntity.getType())
						.createdAt(cardEntity.getCreatedAt()).expireAt(cardEntity.getExpireAt())
						.companyContext(cardEntity.getCompany().getUrlContext())
						.userContext(cardEntity.getUser().getUrlContext()).active(cardEntity.getActive()).build());
			});
		}

		return GroupData.builder().uuid(groupEntity.getUuid().toString()).name(groupEntity.getName())
				.urlContext(groupEntity.getUrlContext()).img(groupEntity.getImg())
				.description(groupEntity.getDescription()).active(groupEntity.getActive()).adminUsers(adminUsers)
				.cards(cardData).build();
	}

}
