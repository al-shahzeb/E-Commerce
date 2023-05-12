package com.example.Great_Indian_Bazaar.dto.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerResponse {
    int id;
    String name;
    String contact;
}
