package com.example.Great_Indian_Bazaar.dto.responseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String orderNo;
    int amount;
    Date orderedOn;
    String cardUsed;
}
