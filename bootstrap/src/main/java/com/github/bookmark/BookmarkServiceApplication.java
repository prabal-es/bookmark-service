package com.github.bookmark;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.bookmark.domain.CardDomain;
import com.github.bookmark.domain.CompanyDomain;
import com.github.bookmark.domain.GroupDomain;
import com.github.bookmark.domain.UserDomain;
import com.github.bookmark.domain.port.CardDomainPort;
import com.github.bookmark.domain.port.CompanyDomainPort;
import com.github.bookmark.domain.port.GroupDomainPort;
import com.github.bookmark.domain.port.UserDomainPort;
import com.github.bookmark.jpa.CardJpaAdapter;
import com.github.bookmark.jpa.CompanyJpaAdapter;
import com.github.bookmark.jpa.GroupJpaAdapter;
import com.github.bookmark.jpa.UserJpaAdapter;
import com.github.bookmark.jpa.port.CardJpaPort;
import com.github.bookmark.jpa.port.CompanyJpaPort;
import com.github.bookmark.jpa.port.GroupJpaPort;
import com.github.bookmark.jpa.port.UserJpaPort;
import com.github.bookmark.jpa.repository.CardRepository;
import com.github.bookmark.jpa.repository.CompanyRepository;
import com.github.bookmark.jpa.repository.GroupRepository;
import com.github.bookmark.jpa.repository.UserRepository;

@SpringBootApplication
public class BookmarkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkServiceApplication.class, args);
	}

	@Bean
	public CompanyJpaPort getCompanyJpaPort(final CompanyRepository companyRepository) {
		return new CompanyJpaAdapter(companyRepository);
	}

	@Bean
	public UserJpaPort getUserJpaPort(final UserRepository userRepository) {
		return new UserJpaAdapter(userRepository);
	}

	@Bean
	public GroupJpaPort getGroupJpaPort(final GroupRepository groupRepository, final UserRepository userRepository, final CardRepository cardRepository) {
		return new GroupJpaAdapter(groupRepository, userRepository, cardRepository);
	}

	@Bean
	public CardJpaPort geCardJpaPort(final CardRepository cardRepository, final UserRepository userRepository) {
		return new CardJpaAdapter(cardRepository, userRepository);
	}

	@Bean
	public CompanyDomainPort getCompanyDomainPort(final CompanyJpaPort companyJpaPort, final UserJpaPort userJpaPort,
			GroupJpaPort groupJpaPort) {
		return new CompanyDomain(companyJpaPort, userJpaPort, groupJpaPort);
	}

	@Bean
	public UserDomainPort getUserDomainPort(final UserJpaPort userJpaPort, final GroupJpaPort groupJpaPort) {
		return new UserDomain(userJpaPort);
	}

	@Bean
	public CardDomainPort getCardDomainPort(final CardJpaPort cardJpaPort) {
		return new CardDomain(cardJpaPort);
	}

	@Bean
	public GroupDomainPort getGroupDomainPort(final GroupJpaPort groupJpaPort) {
		return new GroupDomain(groupJpaPort);
	}

	@Bean
	public UrlValidator getUrlValidator() {
		return UrlValidator.getInstance();
	}
}
