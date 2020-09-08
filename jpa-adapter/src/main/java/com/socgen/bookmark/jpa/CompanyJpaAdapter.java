package com.socgen.bookmark.jpa;

import java.util.ArrayList;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;
import com.socgen.bookmark.jpa.repository.CompanyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyJpaAdapter implements CompanyJpaPort {

	private final CompanyRepository companyRepository;

	@Override
	public Company getCompanies() {
		var companies = new ArrayList<CompanyData>();
		companyRepository.findAll().stream().forEach(entity -> {
			companies.add(CompanyData.builder().id(entity.getId()).name(entity.getName())
					.description(entity.getDescription()).active(entity.isActive()).build());
		});
		return Company.builder().data(companies).build();
	}

}
