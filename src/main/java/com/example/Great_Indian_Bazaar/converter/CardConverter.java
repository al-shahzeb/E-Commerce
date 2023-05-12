package com.example.Great_Indian_Bazaar.converter;

import com.example.Great_Indian_Bazaar.dto.requestDto.CardRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CardResponse;
import com.example.Great_Indian_Bazaar.model.Card;

public class CardConverter {
    public static Card CardRequestToCard(CardRequest cardRequest){
        return Card.builder()
                .cardNumber(cardRequest.getCardNumber())
                .cardType(cardRequest.getCardType())
                .cvv(cardRequest.getCvv())
                .expiryDate(cardRequest.getExpiryDate())
                .build();
    }

    public static CardResponse CardToCardResponse(Card card,String name){
        return CardResponse.builder()
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .cvv(card.getCvv())
                .belongsTo(name)
                .build();
    }
}
