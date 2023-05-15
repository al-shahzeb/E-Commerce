package com.example.Great_Indian_Bazaar.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlaceOrderRequest {
    String cardNumber;
    int cvv;
    int customerId;
}
