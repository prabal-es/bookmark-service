package com.socgen.bookmark.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	public CompanyData getCompanyByUuid(final String uuid) {
		Optional<CompanyEntity> companyEntityOptional = companyRepository.findById(UUID.fromString(uuid));
		if (companyEntityOptional.isPresent()) {
			return mapCompanyData(companyEntityOptional.get());
		} else {
			return null;
		}
	}

	private CompanyData mapCompanyData(final CompanyEntity companyEntity) {
		return CompanyData.builder().uuid(companyEntity.getUuid().toString()).name(companyEntity.getName())
				.description(companyEntity.getDescription()).active(companyEntity.isActive()).build();
	}

	private Company mapCompanies(final List<CompanyEntity> companyEntities) {
		var companies = new ArrayList<CompanyData>();
		companyEntities.forEach(companyEntity -> {
			companies.add(mapCompanyData(companyEntity));
		});
		return Company.builder().data(companies).build();
	}

}
