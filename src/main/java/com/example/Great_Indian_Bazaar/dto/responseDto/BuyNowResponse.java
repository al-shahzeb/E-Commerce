package com.example.Great_Indian_Bazaar.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BuyNowResponse {
    String product;
    int quantity;
    int total;
    String orderedBy;
    String withCard;
    String orderNumber;
    String orderedOn;

}
