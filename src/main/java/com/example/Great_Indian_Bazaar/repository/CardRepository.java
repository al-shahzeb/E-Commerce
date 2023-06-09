package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.enums.CardType;
import com.example.Great_Indian_Bazaar.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {

    List<Card> findByCardType(CardType cardType);

    Card findByCvv(int cvv);

    Card findByCardNumber(String cardNumber);

    @Query(value = "select card_type from card c Group By c.card_type Order By COUNT(*) DESC LIMIT 1", nativeQuery = true)
    CardType findCardWithMaxCount();
}
