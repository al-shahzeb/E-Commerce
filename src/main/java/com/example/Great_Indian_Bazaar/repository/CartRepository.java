package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "update cart c set c.total=0 and c.total_items=0 where id =:id", nativeQuery = true)
    Cart resetCart(int id);
}
