package com.example.Great_Indian_Bazaar.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemToCartResponse {

    String belongsTo;
    int totalItems;
    int grandTotal;
    List<ItemResponse> items;
}
