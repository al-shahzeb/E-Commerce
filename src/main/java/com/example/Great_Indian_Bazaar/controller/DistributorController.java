package com.example.Great_Indian_Bazaar.controller;

import com.example.Great_Indian_Bazaar.dto.requestDto.DistributorRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.DistributorResponse;
import com.example.Great_Indian_Bazaar.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/distributor")
public class DistributorController {

    @Autowired
    DistributorService distributorService;

    @PostMapping("/add")
    public ResponseEntity addDistributor(@RequestBody DistributorRequest distributorRequest) {
        try{
            DistributorResponse distributorResponse = distributorService.addDistributor(distributorRequest);
            return new ResponseEntity<>(distributorResponse,HttpStatus.CREATED);
        }catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-by-email")
    public ResponseEntity getDistributorByEmail(@RequestParam("email")String email){
        try {
            DistributorResponse distributorResponse = distributorService.getByEmail(email);
            return new ResponseEntity<>(distributorResponse,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity getDistributorById(@RequestParam("id")Integer id){
        try {
            DistributorResponse distributorResponse = distributorService.getById(id);
            return new ResponseEntity<>(distributorResponse,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public List<DistributorResponse> getAllDistributor(){
        return distributorService.getAllDistributor();
    }

    @PutMapping("/update/{email}/{contact}")
    public ResponseEntity updateDistributor(@PathVariable("email")String email,
                                            @PathVariable("contact")String contact){

        try{
            DistributorResponse distributorResponse = distributorService.updateDistributor(email,contact);
            return new ResponseEntity<>(distributorResponse,HttpStatus.ACCEPTED);
        }catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable("id")Integer id){
        try {
            return distributorService.deleteById(id);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
