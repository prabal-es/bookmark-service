package com.socgen.bookmark;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.socgen.bookmark.domain.CardDomain;
import com.socgen.bookmark.domain.CompanyDomain;
import com.socgen.bookmark.domain.GroupDomain;
import com.socgen.bookmark.domain.UserDomain;
import com.socgen.bookmark.domain.port.CardDomainPort;
import com.socgen.bookmark.domain.port.CompanyDomainPort;
import com.socgen.bookmark.domain.port.GroupDomainPort;
import com.socgen.bookmark.domain.port.UserDomainPort;
import com.socgen.bookmark.jpa.CardJpaAdapter;
import com.socgen.bookmark.jpa.CompanyJpaAdapter;
import com.socgen.bookmark.jpa.GroupJpaAdapter;
import com.socgen.bookmark.jpa.UserJpaAdapter;
import com.socgen.bookmark.jpa.port.CardJpaPort;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;
import com.socgen.bookmark.jpa.port.GroupJpaPort;
import com.socgen.bookmark.jpa.port.UserJpaPort;
import com.socgen.bookmark.jpa.repository.CardRepository;
import com.socgen.bookmark.jpa.repository.CompanyRepository;
import com.socgen.bookmark.jpa.repository.GroupRepository;
import com.socgen.bookmark.jpa.repository.UserRepository;

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
	public GroupJpaPort getGroupJpaPort(final GroupRepository groupRepository, final UserRepository userRepository) {
		return new GroupJpaAdapter(groupRepository, userRepository);
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
