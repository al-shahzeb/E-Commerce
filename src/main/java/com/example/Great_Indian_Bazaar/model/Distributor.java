package com.example.Great_Indian_Bazaar.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "distributor")
public class Distributor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(nullable = false)
    String name;
    String address;
    String contact;

    @Column(unique = true)
    String email;

    @OneToMany(mappedBy = "distributor", cascade = CascadeType.ALL)
    List<Product> products = new ArrayList<>();

}
