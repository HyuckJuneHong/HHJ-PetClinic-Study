package kr.co.hhjpetclinicstudy.owner.model;

import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;

public class OwnerDtoCreators {

    public static OwnerReqDTO.CREATE ownerReqDto_create_creators(){

        return OwnerReqDTO.CREATE.builder()
                .firstName("testFirstName")
                .lastName("testLastName")
                .address("testAddress")
                .city("testCity")
                .telephone("01000000000")
                .build();
    }
}
