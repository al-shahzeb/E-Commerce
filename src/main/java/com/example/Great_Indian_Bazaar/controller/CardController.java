package com.example.Great_Indian_Bazaar.controller;


import com.example.Great_Indian_Bazaar.dto.requestDto.CardRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CardResponse;
import com.example.Great_Indian_Bazaar.enums.CardType;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public CardResponse addCard(@RequestBody CardRequest cardRequest) throws ResourceNotFoundException {
        return cardService.addCard(cardRequest);
    }

    @GetMapping("/get-cards-of-customer/{id}")
    public List<CardResponse> getCardsOfCustomer(@PathVariable int id) throws ResourceNotFoundException {
        return cardService.getCardsOfCustomer(id);
    }

    @GetMapping("/get-cards-by-type")
    public List<CardResponse> getCardByType(@RequestParam CardType cardType){
        return cardService.getCardByType(cardType);
    }

    @GetMapping("/get-all")
    public List<CardResponse> getAllCards(){
        return cardService.getAllCards();
    }

    //facing issue while passing date in postman
    //API not working
    @GetMapping("/get-cards-after-given-expiry")
    public List<CardResponse> getCardsAfterDate(@RequestParam Date date){
        return cardService.getCardsAfterDate(date);
    }


    //card type occurring maximum times in card table
    @GetMapping("/get-card-with-max-count")
    public String getCardWithMaxCount(){
        return ""+cardService.getCardWithMaxCount();
    }

    //update card type of card with given cvv of given customer
    @PutMapping("/update/{id}/{cvv}")
    public CardResponse updateCard(@PathVariable int id, @PathVariable int cvv,
                                   @RequestParam CardType cardType) throws ResourceNotFoundException {
        return cardService.updateCard(id,cvv,cardType);
    }

    //delete card of customer with given cvv
    @DeleteMapping("/delete/{cvv}")
    public ResponseEntity deleteCard(@PathVariable int cvv){
        try {
            cardService.deleteCard(cvv);
            return new ResponseEntity<>("Card deleted.", HttpStatus.GONE);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
