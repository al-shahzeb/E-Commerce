package com.example.Great_Indian_Bazaar.controller;

import com.example.Great_Indian_Bazaar.dto.requestDto.BuyNowRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.OrderResponse;
import com.example.Great_Indian_Bazaar.model.Ordered;
import com.example.Great_Indian_Bazaar.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderedService orderedService;
    @PostMapping("/buy-now")
    public ResponseEntity buyProduct(@RequestBody BuyNowRequest buyNowRequest){
        try {
            return new ResponseEntity(orderedService.placeOrder(buyNowRequest), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-order-of-customer/{customerId}")
    public List<OrderResponse> getListOfCustomer(@PathVariable int customerId){
        return orderedService.getListOfCustomer(customerId);
    }

    @GetMapping("/recent-orders")
    public List<OrderResponse> getRecentOrders(){
        return orderedService.getRecentOrders();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id){
        try {
            return new ResponseEntity<>(orderedService.deleteOrder(id),HttpStatus.GONE);
        }catch (Exception e){
         return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
