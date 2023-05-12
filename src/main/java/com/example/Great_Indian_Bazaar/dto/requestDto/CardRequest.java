package com.example.Great_Indian_Bazaar.dto.requestDto;

import com.example.Great_Indian_Bazaar.enums.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CardRequest {

    String customerContact;
    int cvv;
    String cardNumber;
    Date expiryDate;
    CardType cardType;
}
