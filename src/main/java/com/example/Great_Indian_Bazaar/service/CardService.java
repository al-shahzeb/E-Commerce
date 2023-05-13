package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.dto.requestDto.CardRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CardResponse;
import com.example.Great_Indian_Bazaar.enums.CardType;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;

import java.util.Date;
import java.util.List;

public interface CardService {
    CardResponse addCard(CardRequest cardRequest) throws ResourceNotFoundException;

    List<CardResponse> getCardsOfCustomer(int id) throws ResourceNotFoundException;

    List<CardResponse> getCardByType(CardType cardType);

    CardResponse updateCard(int id, int cvv, CardType cardType) throws ResourceNotFoundException;

    List<CardResponse> getCardsAfterDate(Date date);

    List<CardResponse> getAllCards();

    CardType getCardWithMaxCount();

    void deleteCard(int cvv) throws ResourceNotFoundException;
}
