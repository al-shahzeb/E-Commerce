package com.example.Great_Indian_Bazaar.service;

import com.example.Great_Indian_Bazaar.dto.requestDto.DistributorRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.DistributorResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DistributorService {

    DistributorResponse addDistributor(DistributorRequest distributorRequest) throws ResourceNotFoundException;

    DistributorResponse getByEmail(String email) throws Exception;
    DistributorResponse getById(int id) throws Exception;
    List<DistributorResponse> getAllDistributor();

    DistributorResponse updateDistributor(String email, String contact) throws Exception;
    String deleteById(int id) throws Exception;
}
