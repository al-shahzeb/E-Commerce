package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.ItemConverter;
import com.example.Great_Indian_Bazaar.converter.OrderConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.ItemRequest;
import com.example.Great_Indian_Bazaar.dto.requestDto.PlaceOrderRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemToCartResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderPlacedResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.*;
import com.example.Great_Indian_Bazaar.repository.*;
import com.example.Great_Indian_Bazaar.service.CartService;
import com.example.Great_Indian_Bazaar.service.ItemService;
import com.example.Great_Indian_Bazaar.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderedService orderedService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public ItemToCartResponse addToCart(ItemRequest itemRequest) throws ResourceNotFoundException {

        ItemToCartResponse itemToCartResponse = itemService.createItem(itemRequest);

        return itemToCartResponse;
    }

    @Override
    public OrderPlacedResponse placeOrder(PlaceOrderRequest placeOrderRequest) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(placeOrderRequest.getCustomerId()).get();
        if(customer==null)
            throw new ResourceNotFoundException("Invalid customer!!");

        Card card = cardRepository.findByCardNumber(placeOrderRequest.getCardNumber());
        if(card==null || card.getCvv()!= placeOrderRequest.getCvv()
                || !card.getCustomer().getContact().equals(customer.getContact()))
            throw new ResourceNotFoundException("Card not found");

        if(customer.getCart().getTotalItems()==0)
            throw new ResourceNotFoundException("Cart is empty!");


        try {
            Cart cart = customer.getCart();
            Ordered ordered = orderedService.placeOrder(customer,card);
            List<ItemResponse> itemResponses = new ArrayList<>();
            for(Item item: cart.getItems()) {
                itemResponses.add(ItemConverter.ItemToItemResponse(item));
            }
            for(int i=0; i<cart.getItems().size(); i++)
                cart.getItems().get(i).setOrdered(ordered);

            OrderPlacedResponse orderPlacedResponse= OrderConverter.OrderToPlacedOrderResponse(ordered);
            orderPlacedResponse.setOrderedBy(customer.getName());
            orderPlacedResponse.setItems(itemResponses);
            orderPlacedResponse.setOrderNo(ordered.getOrderNo());

            OrderedServiceImpl.sendMessage(customer.getName(),ordered.getTotal());
            resetCart(cart);

            return orderPlacedResponse;
        }
        catch (Exception e){
            throw new ResourceNotFoundException(e.getMessage());
        }

    }

    @Override
    public ItemToCartResponse getItemsList(int id) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(id).get();
        if(cart==null)
            throw new ResourceNotFoundException("Cart not found");

        if(cart.getItems().size()==0)
            throw new ResourceNotFoundException("Cart is Empty");


        List<ItemResponse> itemResponses = new ArrayList<>();
        for(Item item : cart.getItems())
            itemResponses.add(ItemConverter.ItemToItemResponse(item));

        return ItemConverter.ItemtoCartResponse(cart,cart.getCustomer(),itemResponses);
    }

    @Override
    public List<ItemResponse> removeItems(int id, String name) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(id).get();
        if(cart==null)
            throw new ResourceNotFoundException("Cart not found");

        List<ItemResponse> responses=new ArrayList<>();
        for(Item item: cart.getItems()){
            if(item.getName().equals(name)){
                responses.add(ItemConverter.ItemToItemResponse(item));
                itemRepository.delete(item);
            }
        }

        if(responses.size()!=0){
            Product product = productRepository.findByName(name);
            List<Item> items = product.getItems();
            for(Item item : items){
                if(item.getCart().equals(cart))
                    items.remove(item);
            }
            product.setItems(items);
        }

        return responses;
    }


    private void resetCart(Cart resetCart) {
        resetCart.setTotal(0);
        resetCart.setTotalItems(0);
        resetCart.setItems(new ArrayList<>());
    }

}
