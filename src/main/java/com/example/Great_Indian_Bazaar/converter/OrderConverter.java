package com.example.Great_Indian_Bazaar.converter;

import com.example.Great_Indian_Bazaar.dto.responseDto.BuyNowResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderPlacedResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderResponse;
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

    public static BuyNowResponse OrderToBuyNowResponse(Ordered saved){
        return BuyNowResponse.builder()
                .total(saved.getTotal())
                .quantity(saved.getItems().get(0).getQuantity())
                .withCard(saved.getCardUsed())
                .orderedOn(String.valueOf(saved.getOrderedOn()))
                .orderNumber(saved.getOrderNo())
                .build();
    }

    public static OrderResponse OrderToOrderResponse(Ordered ordered){
        return OrderResponse.builder()
                .amount(ordered.getTotal())
                .cardUsed(ordered.getCardUsed())
                .orderedOn(ordered.getOrderedOn())
                .orderNo(ordered.getOrderNo())
                .build();
    }
}
