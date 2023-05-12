package com.example.Great_Indian_Bazaar.dto.responseDto;

import com.example.Great_Indian_Bazaar.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponse {

    int cvv;
    String cardNumber;
    String belongsTo;
    CardType cardType;
}
