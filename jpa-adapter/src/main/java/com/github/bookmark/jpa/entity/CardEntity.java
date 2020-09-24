package com.github.bookmark.jpa.entity;

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

import com.github.bookmark.domain.model.Card.CardType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor
@Table(name = "t_card")
public class CardEntity implements Serializable {

	private static final long serialVersionUID = -6623648340723904656L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "UUID", updatable = false, nullable = false)
	@ColumnDefault("uuid_generate_v4()")
	private UUID uuid;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "tiny_url")
	private String tinyUrl;

	@Column(name = "detail_url")
	private String detailUrl;

	@Column(name = "img")
	private String img;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "type", columnDefinition = "VARCHAR(10)")
	@Enumerated(EnumType.STRING)
	private CardType type;
	
	@Column(name = "created_at")
	private Long createdAt;
	
	@Setter
	@Column(name = "expire_at")
	private Long expireAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMAPNY_ID", nullable = false)
	private CompanyEntity company;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	private UserEntity user;
}
