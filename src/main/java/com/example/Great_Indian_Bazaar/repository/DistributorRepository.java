package com.example.Great_Indian_Bazaar.repository;

import com.example.Great_Indian_Bazaar.model.Distributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor,Integer> {

    Distributor findByEmail(String email);
}
