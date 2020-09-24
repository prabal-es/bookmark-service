package com.github.bookmark.jpa.port;

import com.github.bookmark.domain.model.Card;
import com.github.bookmark.domain.model.Card.CardData;
import com.github.bookmark.domain.model.Card.CardType;

public interface CardJpaPort {

	Card getCards(String companyContext, String userContext, CardType type);

	CardData createCard(String companyContext, String userContext, CardData cardData);

	String getCardUrl(String urlContext, String tinyCode);
	
}
