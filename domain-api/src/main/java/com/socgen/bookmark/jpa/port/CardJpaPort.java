package com.socgen.bookmark.jpa.port;

import com.socgen.bookmark.domain.model.Card;
import com.socgen.bookmark.domain.model.Card.CardData;
import com.socgen.bookmark.domain.model.Card.CardType;

public interface CardJpaPort {

	Card getCards(String companyContext, String userContext, CardType type);

	CardData createCard(String companyContext, String userContext, CardData cardData);

	String getCardUrl(String urlContext, String tinyCode);
	
}
