package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.CustomerConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.CustomerRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CustomerResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetCustomerResponse;
import com.example.Great_Indian_Bazaar.enums.CardType;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.Card;
import com.example.Great_Indian_Bazaar.model.Cart;
import com.example.Great_Indian_Bazaar.model.Customer;
import com.example.Great_Indian_Bazaar.repository.CustomerRepository;
import com.example.Great_Indian_Bazaar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest) {
        Customer customer = CustomerConverter.CustomerRequestToCustomer(customerRequest);
        Cart cart = new Cart();
        cart.setTotal(0);
        cart.setTotalItems(0);
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer saved = customerRepository.save(customer);

        return CustomerConverter.CustomerToCustomerResponse(saved);
    }

    @Override
    public GetCustomerResponse getCustomerById(int id) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).get();
        if(customer==null)
            throw new ResourceNotFoundException("Customer not found");

        return CustomerConverter.CustomerToGetCustomerResponse(customer);
    }

    @Override
    public List<GetCustomerResponse> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();

        List<GetCustomerResponse> responses = new ArrayList<>();

        for(Customer customer : customers)
            responses.add(CustomerConverter.CustomerToGetCustomerResponse(customer));

        return responses;
    }

    @Override
    public List<CustomerResponse> getCustomerByCardType(CardType type) {
        List<Customer> customers = customerRepository.findAll();

        List<Customer> filteredCustomers = new ArrayList<>();

        for(Customer customer:customers){
            for(Card card : customer.getCards())
                if(card.getCardType()==type)
                    filteredCustomers.add(customer);
        }

        List<CustomerResponse> responses = new ArrayList<>();

        for(Customer customer : customers)
            responses.add(CustomerConverter.CustomerToCustomerResponse(customer));

        return responses;
    }

    @Override
    public void deleteCustomerByField(String field) throws ResourceNotFoundException {
        Customer customer = null;
        if(field.length()==10)
            customer = customerRepository.findByContact(field);
        else customer = customerRepository.findById(Integer.parseInt(field)).get();

        if(customer==null)
            throw new ResourceNotFoundException("Invalid field!!");

        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponse updateCustomer(int id, String address) throws ResourceNotFoundException {
        Customer customer = customerRepository.findById(id).get();
        if(customer==null)
            throw new ResourceNotFoundException("Customer not found");

        customer.setAddress(address);
        customerRepository.save(customer);

        return CustomerConverter.CustomerToCustomerResponse(customer);
    }
}
