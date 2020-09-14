package com.socgen.bookmark.jpa.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import com.socgen.bookmark.domain.model.User.ROLE;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor
@Table(name = "t_user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -6623648340723904656L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "UUID", updatable = false, nullable = false)
	@ColumnDefault("uuid_generate_v4()")
	private UUID uuid;

	@Column(name = "name")
	private String name;
	
	@Column(name = "url_context")
	private String urlContext;

	@Column(name = "role", columnDefinition = "VARCHAR(10)")
	@Enumerated(EnumType.STRING)
	private ROLE role;

	@Column(name = "img")
	private String img;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "active")
	private Boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMAPNY_ID", nullable = false)
	private CompanyEntity company;
}
