package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.ItemConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.ItemRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemToCartResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.*;
import com.example.Great_Indian_Bazaar.repository.CartRepository;
import com.example.Great_Indian_Bazaar.repository.CustomerRepository;
import com.example.Great_Indian_Bazaar.service.CartService;
import com.example.Great_Indian_Bazaar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ItemToCartResponse addToCart(ItemRequest itemRequest) throws ResourceNotFoundException {

        ItemToCartResponse itemToCartResponse = itemService.createItem(itemRequest);

        return itemToCartResponse;
    }
}
