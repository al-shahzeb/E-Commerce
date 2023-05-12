package com.example.Great_Indian_Bazaar.controller;


import com.example.Great_Indian_Bazaar.dto.requestDto.ProductRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.AvailableProductResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetProductResponse;
import com.example.Great_Indian_Bazaar.enums.ProductCategory;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import com.example.Great_Indian_Bazaar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest){
        try {
            return new ResponseEntity<>(productService.addProduct(productRequest),HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getProduct(@PathVariable int id){
        try {
            return new ResponseEntity<>(productService.getById(id),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public List<GetProductResponse> getAllProduct(){
        return productService.getAll();
    }

    @GetMapping("/get-product-by-distributor-email/{email}")
    public ResponseEntity getProductResponses(@PathVariable String email){
        try {
            return new ResponseEntity(productService.getProductResponses(email),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-products-by-availability")
    public List<AvailableProductResponse> getAvailableProduct(@RequestParam("status")ProductStatus status){
        return productService.getAvailableProducts(status);
    }

    @GetMapping("/available-5costliest-products")
    public List<AvailableProductResponse> getCostliestAvailableProducts(){
        return productService.getCostliestAvailableProducts(ProductStatus.AVAILABLE);
    }

    @GetMapping("/get-costliest-in-category")
    public ResponseEntity costliestInCategory(@RequestParam("productCategory") ProductCategory productCategory){
        try {
            return new ResponseEntity<>(productService.costliestInCategory(productCategory),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
