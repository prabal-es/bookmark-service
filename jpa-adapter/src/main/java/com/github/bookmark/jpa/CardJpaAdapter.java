package com.github.bookmark.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;

import com.github.bookmark.domain.model.Card;
import com.github.bookmark.domain.model.Card.CardData;
import com.github.bookmark.domain.model.Card.CardType;
import com.github.bookmark.jpa.entity.CardEntity;
import com.github.bookmark.jpa.entity.CompanyEntity;
import com.github.bookmark.jpa.entity.UserEntity;
import com.github.bookmark.jpa.port.CardJpaPort;
import com.github.bookmark.jpa.repository.CardRepository;
import com.github.bookmark.jpa.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardJpaAdapter implements CardJpaPort {

	private final CardRepository cardRepository;

	private final UserRepository userRepository;

	@Override
	public Card getCards(String companyContext, String userContext, CardType type) {
		return mapCards(cardRepository
				.findAll(Example
						.of(CardEntity.builder().company(CompanyEntity.builder().urlContext(companyContext).build())
								.user(UserEntity.builder().urlContext(userContext).build()).type(type).build()))
				.stream().filter(card -> card.getExpireAt() == null || card.getExpireAt() >= System.currentTimeMillis())
				.collect(Collectors.toList()));
	}

	@Override
	public CardData createCard(final String companyContext, final String userContext, CardData cardData) {
		CardEntity cardEntity = null;
		Optional<UserEntity> userOptional = userRepository
				.findOne(Example.of(UserEntity.builder().urlContext(userContext).build()));
		if (userOptional.isPresent()) {
			UserEntity user = userOptional.get();
			cardEntity = CardEntity.builder().name(cardData.getName()).description(cardData.getDescription())
					.tinyUrl(cardData.getTinyUrl()).detailUrl(cardData.getDetailUrl()).img(cardData.getImg())
					.type(cardData.getType()).createdAt(cardData.getCreatedAt()).expireAt(cardData.getExpireAt())
					.active(true).user(user).company(user.getCompany()).build();
			cardEntity = cardRepository.save(cardEntity);
		}
		return mapCardData(cardEntity);
	}

	@Override
	public String getCardUrl(final String urlContext, final String tinyCode) {
		String detailUrl = null;
		Optional<CardEntity> cardEntityOptional = cardRepository
				.findOne(Example.of(CardEntity.builder().tinyUrl(tinyCode).build()));
		if (cardEntityOptional.isPresent()) {
			CardEntity entity = cardEntityOptional.get();
			if (null == entity.getExpireAt() || entity.getExpireAt() >= System.currentTimeMillis()) {
				detailUrl = entity.getDetailUrl();
			}
		}
		return detailUrl;
	}

	private Card mapCards(final List<CardEntity> cardEntities) {
		var cards = new ArrayList<CardData>();
		cardEntities.forEach(cardEntity -> {
			cards.add(mapCardData(cardEntity));
		});
		return Card.builder().data(cards).build();
	}

	private CardData mapCardData(final CardEntity cardEntity) {
		return CardData.builder().uuid(cardEntity.getUuid().toString()).name(cardEntity.getName())
				.description(cardEntity.getDescription()).tinyUrl(cardEntity.getTinyUrl())
				.detailUrl(cardEntity.getDetailUrl()).img(cardEntity.getImg()).type(cardEntity.getType())
				.createdAt(cardEntity.getCreatedAt()).expireAt(cardEntity.getExpireAt())
				.companyContext(cardEntity.getCompany().getUrlContext())
				.userContext(cardEntity.getUser().getUrlContext()).active(cardEntity.getActive()).build();
	}
}
