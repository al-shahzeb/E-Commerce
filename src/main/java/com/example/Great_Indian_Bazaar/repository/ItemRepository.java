package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
