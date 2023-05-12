package com.example.Great_Indian_Bazaar.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    int id;
    int total;
    int totalItems;
}
