package com.socgen.bookmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.socgen.bookmark.domain.CompanyDomain;
import com.socgen.bookmark.domain.port.CompanyDomainPort;
import com.socgen.bookmark.jpa.CompanyJpaAdapter;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;
import com.socgen.bookmark.jpa.repository.CompanyRepository;

@SpringBootApplication
public class BookmarkServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookmarkServiceApplication.class, args);
	}
	
	@Bean
	public CompanyJpaPort getCompanyJpaPort(CompanyRepository companyRepository) {
		return new CompanyJpaAdapter(companyRepository);
	}
	
	@Bean
	public CompanyDomainPort getCompanyDomainPort(CompanyJpaPort companyJpaPort) {
		return new CompanyDomain(companyJpaPort);
	}
}
