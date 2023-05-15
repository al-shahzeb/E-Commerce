package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.ItemConverter;
import com.example.Great_Indian_Bazaar.converter.OrderConverter;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderPlacedResponse;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.*;
import com.example.Great_Indian_Bazaar.repository.OrderedRepository;
import com.example.Great_Indian_Bazaar.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderedServiceImpl implements OrderedService {
    @Autowired
    OrderedRepository orderedRepository;
    @Override
    public Ordered placeOrder(Customer customer,Card card) throws ResourceNotFoundException {
        Cart cart = customer.getCart();

        List<Item> newList = new ArrayList<>();
        for(Item item: cart.getItems()){
            try{
                decreaseQuantity(item);
                newList.add(item);
            }catch (Exception e){
                throw new ResourceNotFoundException(e.getMessage());
            }
        }
        Ordered ordered = new Ordered();

        String masked = generateMaskedCardNumber(card.getCardNumber());

        ordered.setCardUsed(masked);
        ordered.setTotal(cart.getTotal());
        ordered.setCustomer(customer);
        ordered.setItems(newList);

        customer.getOrders().add(ordered);

        return orderedRepository.save(ordered);

    }

    private void decreaseQuantity(Item item) throws ResourceNotFoundException {
        Product product = item.getProduct();
        int newQuantity = product.getQuantity()-item.getQuantity();
        if(newQuantity<0)
            throw new ResourceNotFoundException("Invalid order!!");
        product.setQuantity(newQuantity);
        if(product.getQuantity()==0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);

    }

    /***   Not working hibernate error
    private boolean validOrder(List<Item> items) {
        int x=0;
        for(Item item:items){
            Product product = item.getProduct();
            int newQuantity = product.getQuantity()-item.getQuantity();
            if(newQuantity<0) {
                increaseQuantity(items, x);
                return false;
            }
            product.setQuantity(newQuantity);
            if(product.getQuantity()==0)
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            x++;
        }
        return true;
    }

    private void increaseQuantity(List<Item> items, int x) {

        for(int i=0; i<x; i++) {
            Product product = items.get(i).getProduct();
            int newQuantity = product.getQuantity() + items.get(i).getQuantity();
            product.setQuantity(newQuantity);
        }
    }
    ***/

    private String generateMaskedCardNumber(String cardNumber) {
        String masked="";
        for(int i=0; i<cardNumber.length(); i++){
            int n = cardNumber.length()-4;
            if(i>=n)
                masked+=""+cardNumber.charAt(i);
            else masked+="X";
        }
        return masked;
    }
}
