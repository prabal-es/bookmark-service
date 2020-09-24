package com.github.bookmark.domain;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;
import com.github.bookmark.domain.model.Card;
import com.github.bookmark.domain.model.Card.CardData;
import com.github.bookmark.domain.model.Card.CardType;
import com.github.bookmark.domain.port.CardDomainPort;
import com.github.bookmark.jpa.port.CardJpaPort;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CardDomain implements CardDomainPort {

	private final CardJpaPort cardJpaPort;

	public static final long DEFAULT_EXPIRE = 60 * 1000;// 1 min = 60s* 1000mili

	@Override
	public Card getCards(final String companyContext, final String userContext, final CardType type) {
		return cardJpaPort.getCards(companyContext, userContext, type);
	}

	@Override
	public CardData createCard(final String companyContext, final String userContext, final CardData cardData) {
		cardData.setCreatedAt(System.currentTimeMillis());
		if (null == cardData.getExpireAt()) {
			cardData.setExpireAt(DEFAULT_EXPIRE);
		}
		cardData.setTinyUrl(Hashing.murmur3_32() // 8 char output
				.hashString(cardData.getDetailUrl() + System.currentTimeMillis(), StandardCharsets.UTF_8).toString());
		cardData.setExpireAt(System.currentTimeMillis() + cardData.getExpireAt()); // milliseconds
		return cardJpaPort.createCard(companyContext, userContext, cardData);
	}

	@Override
	public String getCardUrl(final String urlContext, final String tinyCode) {
		return cardJpaPort.getCardUrl(urlContext, tinyCode);
	}

}
