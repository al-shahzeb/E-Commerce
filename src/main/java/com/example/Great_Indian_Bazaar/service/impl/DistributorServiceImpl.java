package com.example.Great_Indian_Bazaar.service.impl;

import com.example.Great_Indian_Bazaar.converter.DistributorConverter;
import com.example.Great_Indian_Bazaar.dto.requestDto.DistributorRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.DistributorResponse;
import com.example.Great_Indian_Bazaar.exception.ResourceNotFoundException;
import com.example.Great_Indian_Bazaar.model.Distributor;
import com.example.Great_Indian_Bazaar.repository.DistributorRepository;
import com.example.Great_Indian_Bazaar.service.DistributorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DistributorServiceImpl implements DistributorService {

    @Autowired
    DistributorRepository distributorRepository;

    @Override
    public DistributorResponse addDistributor(DistributorRequest distributorRequest) throws ResourceNotFoundException {

        if(distributorRepository.findByEmail(distributorRequest.getEmail()) != null)
            throw new ResourceNotFoundException("Redundant");

        Distributor distributor = DistributorConverter.DistributorRequestToDistributor(distributorRequest);
        Distributor saved = distributorRepository.save(distributor);


        DistributorResponse distributorResponse = DistributorConverter.DistributorToDistributorResponse(saved);

        return distributorResponse;
    }

    @Override
    public DistributorResponse getByEmail(String email) throws Exception {
        Distributor distributor = distributorRepository.findByEmail(email);
        if(distributor == null)
            throw new Exception("Not Found");

        return DistributorConverter.DistributorToDistributorResponse(distributor);
    }

    @Override
    public DistributorResponse getById(int id) throws Exception {
        Distributor distributor = distributorRepository.findById(id).get();
        if(distributor == null)
            throw new Exception("Not Found");

        return DistributorConverter.DistributorToDistributorResponse(distributor);
    }

    @Override
    public List<DistributorResponse> getAllDistributor() {
        List<Distributor> distributors = distributorRepository.findAll();

        List<DistributorResponse> responseList = new ArrayList<>();
        for(Distributor distributor: distributors)
            responseList.add(DistributorConverter.DistributorToDistributorResponse(distributor));

        return responseList;
    }

    @Override
    public DistributorResponse updateDistributor(String email, String contact) throws Exception {
        Distributor distributor = distributorRepository.findByEmail(email);
        if(distributor == null)
            throw new Exception("Not Found");

        distributor.setContact(contact);
        Distributor saved = distributorRepository.save(distributor);

        return DistributorConverter.DistributorToDistributorResponse(saved);
    }

    @Override
    public String deleteById(int id) throws Exception {
        Distributor distributor = distributorRepository.findById(id).get();
        if(distributor == null)
            throw new Exception("Not Found");

        distributorRepository.delete(distributor);
        return "Distributor deleted";
    }
}
