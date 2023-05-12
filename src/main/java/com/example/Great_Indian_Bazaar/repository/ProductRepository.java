package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.enums.ProductCategory;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import com.example.Great_Indian_Bazaar.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByProductStatus(ProductStatus status);
    List<Product> findByProductCategory(ProductCategory productCategory);
}
