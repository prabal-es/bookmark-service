package com.socgen.bookmark.domain.port;

import com.socgen.bookmark.domain.model.Card;
import com.socgen.bookmark.domain.model.Card.CardData;
import com.socgen.bookmark.domain.model.Card.CardType;

public interface CardDomainPort {

	Card getCards(String companyContext, String userContext, CardType type);

	CardData createCard(String companyContext, String userContext, CardData cardData);
}
