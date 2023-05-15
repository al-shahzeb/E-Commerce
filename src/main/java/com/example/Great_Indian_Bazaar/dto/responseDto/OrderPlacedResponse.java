package com.example.Great_Indian_Bazaar.dto.responseDto;

import com.example.Great_Indian_Bazaar.enums.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderPlacedResponse {
    String orderNo;
    String orderedBy;
    int totalAmount;
    Date orderedOn;
    String cardUsed;
    List<ItemResponse> items;
}
