package com.example.Great_Indian_Bazaar.controller;

import com.example.Great_Indian_Bazaar.dto.requestDto.BuyNowRequest;
import com.example.Great_Indian_Bazaar.dto.requestDto.ItemRequest;
import com.example.Great_Indian_Bazaar.dto.requestDto.PlaceOrderRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ItemToCartResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.service.CartService;
import com.example.Great_Indian_Bazaar.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add-to-cart")
    public ResponseEntity addToCart(@RequestBody ItemRequest itemRequest){
        try {
            return new ResponseEntity(cartService.addToCart(itemRequest), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/place-order")
    public ResponseEntity placeOrder(@RequestBody PlaceOrderRequest placeOrderRequest){
        try {
            return new ResponseEntity<>(cartService.placeOrder(placeOrderRequest),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/items-list/{id}")
    public ResponseEntity getItemsList(@PathVariable int id){
        try {
            return new ResponseEntity<>(cartService.getItemsList(id),HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/remove-item/{id}/{name}")
    public List<ItemResponse> removeItems(@PathVariable int id, @PathVariable String name) throws ResourceNotFoundException {
        return cartService.removeItems(id,name);
    }

}
