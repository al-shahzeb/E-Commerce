package com.example.Great_Indian_Bazaar.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCustomerResponse {
    String name;
    int age;
    String contact;
    CartResponse cart;
}
