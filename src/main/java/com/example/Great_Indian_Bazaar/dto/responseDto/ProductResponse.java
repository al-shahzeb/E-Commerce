package com.example.Great_Indian_Bazaar.dto.responseDto;

import com.example.Great_Indian_Bazaar.enums.ProductCategory;
import com.example.Great_Indian_Bazaar.enums.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    String name;
    int quantity;
    int price;
    ProductStatus status;
    ProductCategory productCategory;
}
