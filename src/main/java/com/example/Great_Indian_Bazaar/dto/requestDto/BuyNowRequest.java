package com.example.Great_Indian_Bazaar.dto.requestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuyNowRequest {
    int productId;
    int quantity;
    int customerId;
    String cardNumber;
    int cvv;

}
