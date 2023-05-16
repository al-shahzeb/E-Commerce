package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered,Integer> {

    @Query(value = "select * from ordered o where o.customer_id=:id", nativeQuery = true)
    List<Ordered> findByCustomerId(int id);

    @Query(value = "select * from ordered LIMIT 5", nativeQuery = true)
    List<Ordered> findRecentOrders();
}
