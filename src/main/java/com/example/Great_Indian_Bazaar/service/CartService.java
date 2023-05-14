package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.dto.requestDto.ItemRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemToCartResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;

public interface CartService {
    ItemToCartResponse addToCart(ItemRequest itemRequest) throws ResourceNotFoundException;
}
