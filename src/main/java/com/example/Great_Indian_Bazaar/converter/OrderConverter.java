package com.example.Great_Indian_Bazaar.converter;

import com.example.Great_Indian_Bazaar.dto.responseDto.OrderPlacedResponse;
import com.example.Great_Indian_Bazaar.model.Ordered;

public class OrderConverter {
    public static OrderPlacedResponse OrderToPlacedOrderResponse(Ordered ordered){

        return OrderPlacedResponse.builder()
                .orderNo(ordered.getOrderNo())
                .orderedOn(ordered.getOrderedOn())
                .totalAmount(ordered.getTotal())
                .cardUsed(ordered.getCardUsed())
                .build();
    }
}
