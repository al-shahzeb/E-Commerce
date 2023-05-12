package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.dto.requestDto.CustomerRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CustomerResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetCustomerResponse;
import com.example.Great_Indian_Bazaar.enums.CardType;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);

    GetCustomerResponse getCustomerById(int id) throws ResourceNotFoundException;

    List<GetCustomerResponse> getAllCustomer();

    List<CustomerResponse> getCustomerByCardType(CardType type);

    void deleteCustomerByField(String field) throws ResourceNotFoundException;

    CustomerResponse updateCustomer(int id, String address) throws ResourceNotFoundException;
}
