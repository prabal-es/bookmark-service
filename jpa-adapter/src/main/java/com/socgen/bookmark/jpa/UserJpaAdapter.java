package com.socgen.bookmark.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Example;

import com.socgen.bookmark.domain.model.User;
import com.socgen.bookmark.domain.model.User.UserData;
import com.socgen.bookmark.jpa.entity.CompanyEntity;
import com.socgen.bookmark.jpa.entity.UserEntity;
import com.socgen.bookmark.jpa.port.UserJpaPort;
import com.socgen.bookmark.jpa.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserJpaAdapter implements UserJpaPort {

	private final UserRepository userRepository;

	@Override
	public User getCompanyUsers(String comapnyUrlContext) {
		return mapUsers(userRepository.findAll(Example.of(
				UserEntity.builder().company(CompanyEntity.builder().urlContext(comapnyUrlContext).build()).build())));
	}

	@Override
	public User getCompanyActiveUsers(String comapnyUrlContext) {
		return mapUsers(userRepository.findAll(Example.of(UserEntity.builder()
				.company(CompanyEntity.builder().urlContext(comapnyUrlContext).build()).active(Boolean.TRUE).build())));
	}

	@Override
	public User getCompanyInactiveUsers(String comapnyUrlContext) {
		return mapUsers(userRepository.findAll(
				Example.of(UserEntity.builder().company(CompanyEntity.builder().urlContext(comapnyUrlContext).build())
						.active(Boolean.FALSE).build())));
	}

	private User mapUsers(final List<UserEntity> userEntities) {
		var users = new ArrayList<UserData>();
		userEntities.forEach(userEntity -> {
			users.add(mapUserData(userEntity));
		});
		return User.builder().data(users).build();
	}

	private UserData mapUserData(final UserEntity userEntity) {
		return UserData.builder().uuid(userEntity.getUuid().toString()).name(userEntity.getName())
				.urlContext(userEntity.getUrlContext()).role(userEntity.getRole()).img(userEntity.getImg())
				.url(userEntity.getUrl()).active(userEntity.getActive()).build();
	}

}
