package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.CardConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.CardRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CardResponse;
import com.example.Great_Indian_Bazaar.enums.CardType;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.Card;
import com.example.Great_Indian_Bazaar.model.Customer;
import com.example.Great_Indian_Bazaar.repository.CardRepository;
import com.example.Great_Indian_Bazaar.repository.CustomerRepository;
import com.example.Great_Indian_Bazaar.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public CardResponse addCard(CardRequest cardRequest) throws ResourceNotFoundException {
        Customer customer = customerRepository.findByContact(cardRequest.getCustomerContact());
        if(customer==null)
            throw new ResourceNotFoundException("No customer found");

        Card card = CardConverter.CardRequestToCard(cardRequest);
        card.setCustomer(customer);

        customer.getCards().add(card);

        customerRepository.save(customer);
        return CardConverter.CardToCardResponse(card,customer.getName());
    }

    @Override
    public List<CardResponse> getCardsOfCustomer(int id) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).get();
        if(customer==null) throw new ResourceNotFoundException("No customer with id");

        List<Card> cards = customer.getCards();

        List<CardResponse> responses = new ArrayList<>();

        for(Card card: cards)
            responses.add(CardConverter.CardToCardResponse(card,customer.getName()));

        return responses;
    }

    @Override
    public List<CardResponse> getCardByType(CardType cardType) {
        List<Card> cards = cardRepository.findByCardType(cardType);
        List<CardResponse> responses = new ArrayList<>();

        for(Card card: cards)
            responses.add(CardConverter.CardToCardResponse(card,card.getCustomer().getName()));

        return responses;
    }

    @Override
    public CardResponse updateCard(int id, int cvv, CardType cardType) throws ResourceNotFoundException {

        Customer customer = customerRepository.findById(id).get();
        if(customer==null)
            throw new ResourceNotFoundException("No customer found");

        List<Card> cards = customer.getCards();
        Card card = null;

        int index=-1;
        for(int i=0; i<cards.size(); i++)
            if(cards.get(i).getCvv()==cvv) {
                card = cards.get(i);
                index=i;
            }

        if(card==null)
            throw new ResourceNotFoundException("No card found");

        cards.remove(index);
        card.setCardType(cardType);
        cards.add(card);

        customer.setCards(cards);

        customerRepository.save(customer);

        return CardConverter.CardToCardResponse(card,customer.getName());
    }

    @Override
    public List<CardResponse> getCardsAfterDate(Date date) {
        List<Card> cards = cardRepository.findAll();

        List<Card> filteredCards = new ArrayList<>();

        for(Card card:cards)
            if(card.getExpiryDate().before(date))
                filteredCards.add(card);

        List<CardResponse> responses = new ArrayList<>();

        for(Card card:filteredCards)
            responses.add(CardConverter.CardToCardResponse(card,card.getCustomer().getName()));

        return responses;
    }

    @Override
    public List<CardResponse> getAllCards() {
        List<Card> cards = cardRepository.findAll();

        List<CardResponse> responses = new ArrayList<>();

        for(Card card:cards)
            responses.add(CardConverter.CardToCardResponse(card,card.getCustomer().getName()));

        return responses;
    }

    @Override
    public CardType getCardWithMaxCount() {
        return cardRepository.findCardWithMaxCount();
    }

    @Override
    public void deleteCard(int cvv) throws ResourceNotFoundException {
        Card card = cardRepository.findByCvv(cvv);

        if(card==null)
            throw new ResourceNotFoundException("Card not found!!");
        Customer customer = card.getCustomer();
        int index = -1;
        for(int i=0; i<customer.getCards().size(); i++){
            if(customer.getCards().get(i).getCvv()==cvv)
                index=i;
        }

        customer.getCards().remove(index);
        cardRepository.delete(card);
        customerRepository.save(customer);
    }
}
