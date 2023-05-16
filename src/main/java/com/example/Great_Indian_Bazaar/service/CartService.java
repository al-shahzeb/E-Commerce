package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.dto.requestDto.ItemRequest;
import com.example.Great_Indian_Bazaar.dto.requestDto.PlaceOrderRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemToCartResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderPlacedResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;

import java.util.List;

public interface CartService {
    ItemToCartResponse addToCart(ItemRequest itemRequest) throws ResourceNotFoundException;

    OrderPlacedResponse placeOrder(PlaceOrderRequest placeOrderRequest) throws ResourceNotFoundException;

    ItemToCartResponse getItemsList(int id) throws ResourceNotFoundException;

    List<ItemResponse> removeItems(int id,String name) throws ResourceNotFoundException;
}
