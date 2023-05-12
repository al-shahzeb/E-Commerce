package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.ProductConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.ProductRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.AvailableProductResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetProductResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ProductResponse;
import com.example.Great_Indian_Bazaar.enums.ProductCategory;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.Distributor;
import com.example.Great_Indian_Bazaar.model.Product;
import com.example.Great_Indian_Bazaar.repository.DistributorRepository;
import com.example.Great_Indian_Bazaar.repository.ProductRepository;
import com.example.Great_Indian_Bazaar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    DistributorRepository distributorRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) throws Exception {
        Distributor distributor = distributorRepository.findById(productRequest.getDistributorId()).get();

        if(distributor==null)
            throw new Exception("Not found");


        Product product = ProductConverter.ProductRequestToProduct(productRequest,distributor);

        distributor.getProducts().add(product);
        distributorRepository.save(distributor);

        return ProductConverter.ProductToProductResponse(product);
    }

    @Override
    public GetProductResponse getById(int id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id).get();
        if(product==null)
            throw new ResourceNotFoundException("Not Found");


        return ProductConverter.ProductToGetProductResponse(product);
    }

    @Override
    public List<GetProductResponse> getAll() {
        List<Product> products = productRepository.findAll();

        List<GetProductResponse> responses=new ArrayList<>();
        for(Product product: products)
            responses.add(ProductConverter.ProductToGetProductResponse(product));

        return responses;
    }

    @Override
    public List<ProductResponse> getProductResponses(String email) throws ResourceNotFoundException {
        Distributor distributor = distributorRepository.findByEmail(email);
        if(distributor==null)
            throw new ResourceNotFoundException("Not Found");
        List<Product> products = distributor.getProducts();

        List<ProductResponse> productResponses = new ArrayList<>();
        for(Product product:products)
            productResponses.add(ProductConverter.ProductToProductResponse(product));

        return productResponses;
    }

    @Override
    public List<AvailableProductResponse> getAvailableProducts(ProductStatus status) {
        List<Product> availableProducts = productRepository.findByProductStatus(status);

        List<AvailableProductResponse> productResponses = new ArrayList<>();
        for(Product product : availableProducts)
            productResponses.add(ProductConverter.ProductToAvailableProductResponse(product));

        return productResponses;
    }

    @Override
    public List<AvailableProductResponse> getCostliestAvailableProducts(ProductStatus status) {
        List<Product> availableProducts = productRepository.findByProductStatus(status);

        PriorityQueue<Product> pq = new PriorityQueue<>((a,b)->a.getPrice()-b.getPrice());

        for(Product product: availableProducts){
            pq.add(product);
            if(pq.size()>5)
                pq.remove();
        }

        List<AvailableProductResponse> responses = new ArrayList<>();
        while(!pq.isEmpty())
            responses.add(ProductConverter.ProductToAvailableProductResponse(pq.remove()));

        return responses;
    }

    @Override
    public ProductResponse costliestInCategory(ProductCategory productCategory) throws ResourceNotFoundException {
        List<Product> products = productRepository.findByProductCategory(productCategory);
        if(products.size()==0)
            throw new ResourceNotFoundException("No product available");
        int maxPrice=0;
        Product maxProduct=null;
        for(Product product: products)
            if(maxPrice<product.getPrice()){
                maxPrice=product.getPrice();
                maxProduct=product;
            }

        return ProductConverter.ProductToProductResponse(maxProduct);
    }
}
