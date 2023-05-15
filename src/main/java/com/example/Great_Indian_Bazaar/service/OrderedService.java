package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.Card;
import com.example.Great_Indian_Bazaar.model.Customer;
import com.example.Great_Indian_Bazaar.model.Ordered;

public interface OrderedService {
    Ordered placeOrder(Customer customer, Card card) throws ResourceNotFoundException;
}
