package com.socgen.bookmark.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;

import com.socgen.bookmark.domain.model.Company;
import com.socgen.bookmark.domain.model.Company.CompanyData;
import com.socgen.bookmark.jpa.entity.CompanyEntity;
import com.socgen.bookmark.jpa.port.CompanyJpaPort;
import com.socgen.bookmark.jpa.repository.CompanyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompanyJpaAdapter implements CompanyJpaPort {

	private final CompanyRepository companyRepository;

	@Override
	public Company getCompanies() {
		return mapCompanies(companyRepository.findAll());
	}

	@Override
	public Company getActiveCompanies() {
		return mapCompanies(
				companyRepository.findAll(Example.of(CompanyEntity.builder().active(Boolean.TRUE).build())));
	}

	@Override
	public Company getInactiveCompanies() {
		return mapCompanies(
				companyRepository.findAll(Example.of(CompanyEntity.builder().active(Boolean.FALSE).build())));
	}

	@Override
	public CompanyData getCompanyByUrlContext(final String urlContext) {
		Optional<CompanyEntity> companyEntityOptional = companyRepository
				.findOne((Example.of(CompanyEntity.builder().urlContext(urlContext).build())));
		if (companyEntityOptional.isPresent()) {
			return mapCompanyData(companyEntityOptional.get());
		} else {
			return null;
		}
	}

	private CompanyData mapCompanyData(final CompanyEntity companyEntity) {
		return CompanyData.builder().uuid(companyEntity.getUuid().toString()).name(companyEntity.getName())
				.urlContext(companyEntity.getUrlContext()).description(companyEntity.getDescription())
				.img(companyEntity.getImg()).url(companyEntity.getUrl()).active(companyEntity.getActive())
				.userCount(companyEntity.getUsers().size()).groupCount(companyEntity.getGroups().size()).build();
	}

	private Company mapCompanies(final List<CompanyEntity> companyEntities) {
		var companies = new ArrayList<CompanyData>();
		companyEntities.forEach(companyEntity -> {
			companies.add(mapCompanyData(companyEntity));
		});
		return Company.builder().data(companies).build();
	}

}
