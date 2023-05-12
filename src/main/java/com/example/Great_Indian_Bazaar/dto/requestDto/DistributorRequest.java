package com.example.Great_Indian_Bazaar.dto.requestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DistributorRequest {
    String name;
    String email;
    String contact;
    String address;
}
