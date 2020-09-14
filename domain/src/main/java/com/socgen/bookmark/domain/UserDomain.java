package com.socgen.bookmark.domain;

import com.socgen.bookmark.domain.model.User;
import com.socgen.bookmark.domain.port.UserDomainPort;
import com.socgen.bookmark.jpa.port.UserJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDomain implements UserDomainPort {

	private final UserJpaPort userJpaPort;

	@Override
	public User getUsers() {
		return userJpaPort.getUsers();
	}

}
