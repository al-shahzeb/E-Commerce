package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
