package com.example.Great_Indian_Bazaar.dto.responseDto;

import com.example.Great_Indian_Bazaar.enums.ProductCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AvailableProductResponse {
    String name;
    ProductCategory productCategory;
    int price;
}
