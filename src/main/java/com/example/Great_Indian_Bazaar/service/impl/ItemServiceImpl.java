package com.example.Great_Indian_Bazaar.service.impl;


import com.example.Great_Indian_Bazaar.converter.ItemConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.ItemRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemToCartResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.*;
import com.example.Great_Indian_Bazaar.repository.*;
import com.example.Great_Indian_Bazaar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DistributorRepository distributorRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public ItemToCartResponse createItem(ItemRequest itemRequest) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(itemRequest.getCustomerId()).get();
        if(customer==null)
            throw new ResourceNotFoundException("No customer found!");

        Cart cart = customer.getCart();
        Product product = productRepository.findById(itemRequest.getProductId()).get();
        if(product==null || product.getQuantity()==0 || product.getQuantity()< itemRequest.getQuantity())
            throw new ResourceNotFoundException("Product unavailable");

        Item item = ItemConverter.ItemRequestToItem(itemRequest,cart,product);

        product.getItems().add(item);
        Distributor distributor = product.getDistributor();

        for(Product prd : distributor.getProducts()){
            if(prd.getName().equals(product.getName()))
                prd.getItems().add(item);
        }

        cart.getItems().add(item);
        int updatedTotalItems = cart.getTotalItems()+ item.getQuantity();
        cart.setTotalItems(updatedTotalItems);

        int updatedTotal = cart.getTotal()+(item.getQuantity()*item.getPrice());
        cart.setTotal(updatedTotal);

        List<ItemResponse> itemResponses = new ArrayList<>();
        for(Item item1: cart.getItems()) {
            itemResponses.add(ItemConverter.ItemToItemResponse(item1));
        }

        customer.setCart(cart);

        itemRepository.save(item);

        return ItemConverter.ItemtoCartResponse(cart,customer,itemResponses);
    }
}
