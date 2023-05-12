package com.example.Great_Indian_Bazaar.dto.requestDto;

import com.example.Great_Indian_Bazaar.enums.ProductCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    String name;
    int quantity;
    int price;
    ProductCategory productCategory;
    int distributorId;
}
