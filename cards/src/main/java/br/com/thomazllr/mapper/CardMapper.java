package br.com.thomazllr.mapper;

import br.com.thomazllr.domain.Card;
import br.com.thomazllr.dto.request.CardRequest;
import br.com.thomazllr.dto.response.CardResponse;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public Card toEntity(CardRequest dto) {
        return Card.builder()
                .mobileNumber(dto.getMobileNumber())
                .build();
    }

    public CardResponse toResponse(Card card) {
        return CardResponse.builder()
                .id(card.getCardId())
                .mobileNumber(card.getMobileNumber())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .build();
    }
}
