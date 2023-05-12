package com.example.Great_Indian_Bazaar.controller;

import com.example.Great_Indian_Bazaar.dto.requestDto.CustomerRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.CustomerResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetCustomerResponse;
import com.example.Great_Indian_Bazaar.enums.CardType;
import com.example.Great_Indian_Bazaar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerResponse> addCustomer(@RequestBody CustomerRequest customerRequest){
        return new ResponseEntity<>(customerService.addCustomer(customerRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getCustomerById(@PathVariable int id){
        try {
            return new ResponseEntity(customerService.getCustomerById(id),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-cardType/{type}")
    public List<CustomerResponse> getCustomerByCardType(@PathVariable CardType type){
        return customerService.getCustomerByCardType(type);
    }

    @GetMapping("/get-all")
    public List<GetCustomerResponse> getAllCustomer(){
        return customerService.getAllCustomer();
    }

    @PutMapping("/update/{id}/{address}")
    public ResponseEntity updateCustomer(@PathVariable int id, @PathVariable String address){
        try {
            return new ResponseEntity<>(customerService.updateCustomer(id,address),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete-by-field/{field}")
    public ResponseEntity deleteCustomerByField(@PathVariable String field){
        try {
            customerService.deleteCustomerByField(field);
            return new ResponseEntity<>("Delete customer",HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
