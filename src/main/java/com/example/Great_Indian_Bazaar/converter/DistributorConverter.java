package com.example.Great_Indian_Bazaar.converter;

import com.example.Great_Indian_Bazaar.dto.requestDto.DistributorRequest;
import com.example.Great_Indian_Bazaar.dto.responseDto.DistributorResponse;
import com.example.Great_Indian_Bazaar.model.Distributor;

public class DistributorConverter {

    public static Distributor DistributorRequestToDistributor(DistributorRequest distributorRequest){
        Distributor distributor = Distributor.builder()
                .name(distributorRequest.getName())
                .address(distributorRequest.getAddress())
                .contact(distributorRequest.getContact())
                .email(distributorRequest.getEmail())
                .build();
        return distributor;
    }

    public static DistributorResponse DistributorToDistributorResponse(Distributor distributor){
        DistributorResponse distributorResponse = DistributorResponse.builder()
                .id(distributor.getId())
                .name(distributor.getName())
                .email(distributor.getEmail())
                .build();

        return distributorResponse;
    }
}
