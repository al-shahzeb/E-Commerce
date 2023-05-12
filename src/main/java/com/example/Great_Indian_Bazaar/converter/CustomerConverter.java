package com.example.Great_Indian_Bazaar.converter;

import com.example.Great_Indian_Bazaar.dto.requestDto.CustomerRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CartResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.CustomerResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetCustomerResponse;
import com.example.Great_Indian_Bazaar.model.Customer;

public class CustomerConverter {
    public static Customer CustomerRequestToCustomer(CustomerRequest customerRequest){
        return Customer.builder()
                .age(customerRequest.getAge())
                .name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .contact(customerRequest.getContact())
                .build();
    }

    public static CustomerResponse CustomerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .contact(customer.getContact())
                .build();
    }

    public static GetCustomerResponse CustomerToGetCustomerResponse(Customer customer){
        CartResponse cartResponse = CartResponse.builder()
                .id(customer.getCart().getId())
                .total(customer.getCart().getTotal())
                .totalItems(customer.getCart().getTotalItems())
                .build();

        return GetCustomerResponse.builder()
                .cart(cartResponse)
                .age(customer.getAge())
                .contact(customer.getContact())
                .name(customer.getName())
                .build();
    }
}
