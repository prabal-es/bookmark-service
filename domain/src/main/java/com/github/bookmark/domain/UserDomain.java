package com.github.bookmark.domain;

import com.github.bookmark.domain.model.User;
import com.github.bookmark.domain.port.UserDomainPort;
import com.github.bookmark.jpa.port.UserJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDomain implements UserDomainPort {

	private final UserJpaPort userJpaPort;

	@Override
	public User getUsers() {
		return userJpaPort.getUsers();
	}

}
