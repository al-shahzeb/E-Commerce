package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.dto.requestDto.BuyNowRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.BuyNowResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.Card;
import com.example.Great_Indian_Bazaar.model.Customer;
import com.example.Great_Indian_Bazaar.model.Ordered;

import java.util.List;

public interface OrderedService {
    Ordered placeOrder(Customer customer, Card card) throws ResourceNotFoundException;

    BuyNowResponse placeOrder(BuyNowRequest buyNowRequest) throws ResourceNotFoundException;

    List<OrderResponse> getListOfCustomer(int customerId);

    List<OrderResponse> getRecentOrders();

    OrderResponse deleteOrder(int id) throws ResourceNotFoundException;
}
