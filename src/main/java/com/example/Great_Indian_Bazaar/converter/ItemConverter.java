package com.example.Great_Indian_Bazaar.converter;

import com.example.Great_Indian_Bazaar.dto.requestDto.ItemRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemToCartResponse;
import com.example.Great_Indian_Bazaar.model.Cart;
import com.example.Great_Indian_Bazaar.model.Customer;
import com.example.Great_Indian_Bazaar.model.Item;
import com.example.Great_Indian_Bazaar.model.Product;

import java.util.List;

public class ItemConverter {
    public static Item ItemRequestToItem(ItemRequest itemRequest, Cart cart, Product product){
        return Item.builder()
                .cart(cart)
                .product(product)
                .name(product.getName())
                .quantity(itemRequest.getQuantity())
                .price(product.getPrice())
                .build();
    }

    public static ItemResponse ItemToItemResponse(Item item){
        return ItemResponse.builder()
                .name(item.getName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }

    public static ItemToCartResponse ItemtoCartResponse(Cart cart, Customer customer, List<ItemResponse> itemResponse){
        return ItemToCartResponse.builder()
                .totalItems(cart.getTotalItems())
                .grandTotal(cart.getTotal())
                .items(itemResponse)
                .belongsTo(customer.getName())
                .build();
    }
}
