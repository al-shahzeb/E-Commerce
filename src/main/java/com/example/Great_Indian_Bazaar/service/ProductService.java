package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.dto.requestDto.ProductRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.AvailableProductResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetProductResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ProductResponse;
import com.example.Great_Indian_Bazaar.enums.ProductCategory;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;

import java.util.List;


public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest) throws Exception;

    GetProductResponse getById(int id) throws ResourceNotFoundException;

    List<GetProductResponse> getAll();

    List<ProductResponse> getProductResponses(String email) throws ResourceNotFoundException;

    List<AvailableProductResponse> getAvailableProducts(ProductStatus status);
    List<AvailableProductResponse> getCostliestAvailableProducts(ProductStatus status);
    ProductResponse costliestInCategory(ProductCategory productCategory) throws ResourceNotFoundException;
}
