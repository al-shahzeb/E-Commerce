package com.example.Great_Indian_Bazaar.converter;

import com.example.Great_Indian_Bazaar.dto.requestDto.ProductRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.AvailableProductResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.DistributorResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.GetProductResponse;
import com.example.Great_Indian_Bazaar.dto.responseDto.ProductResponse;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import com.example.Great_Indian_Bazaar.model.Distributor;
import com.example.Great_Indian_Bazaar.model.Product;

public class ProductConverter {


    public static Product ProductRequestToProduct(ProductRequest productRequest, Distributor distributor){

        Product product = Product.builder()
                .name(productRequest.getName())
                .productCategory(productRequest.getProductCategory())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .productStatus(ProductStatus.AVAILABLE)
                .distributor(distributor).build();

        return product;

    }

    public static ProductResponse ProductToProductResponse(Product product){
        return ProductResponse.builder()
                .productCategory(product.getProductCategory())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getProductStatus())
                .build();
    }

    public static GetProductResponse ProductToGetProductResponse(Product product){

        DistributorResponse distributorResponse = DistributorConverter.DistributorToDistributorResponse(product.getDistributor());
        return GetProductResponse.builder()
                .productCategory(product.getProductCategory())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getProductStatus())
                .distributor(distributorResponse)
                .build();
    }

    public static AvailableProductResponse ProductToAvailableProductResponse(Product product){
        return AvailableProductResponse.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productCategory(product.getProductCategory())
                .build();
    }
}
