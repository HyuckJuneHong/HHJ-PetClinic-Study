package kr.co.hhjpetclinicstudy.service.model;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;

public class OwnerCreators {

    public static Owner owner_only_phone(String telephone){

        return Owner.builder()
                .telephone(telephone)
                .build();
    }

    public static OwnerReqDTO.UPDATE ownerReqDTO_update_only_phone(String telephone){

        return OwnerReqDTO.UPDATE.builder()
                .telephone(telephone)
                .build();
    }

    public static OwnerResDTO.READ ownerResDTO_read_only_phone(String telephone){

        return OwnerResDTO.READ.builder()
                .telephone(telephone)
                .build();
    }

    public static OwnerReqDTO.CREATE ownerReqDto_create_creators(){

        return OwnerReqDTO.CREATE.builder()
                .firstName("testFirstName")
                .lastName("testLastName")
                .address("testAddress")
                .city("testCity")
                .telephone("01000000000")
                .build();
    }

    public static OwnerReqDTO.CREATE ownerReqDto_create_creators(String telephone){

        return OwnerReqDTO.CREATE.builder()
                .firstName("testFirstName")
                .lastName("testLastName")
                .address("testAddress")
                .city("testCity")
                .telephone("01000000000")
                .build();
    }

    public static OwnerReqDTO.UPDATE ownerReqDto_update_creators(String telephone){

        return OwnerReqDTO.UPDATE.builder()
                .firstName("updateFirstName")
                .lastName("updateLastName")
                .address("updateAddress")
                .city("updateCity")
                .telephone(telephone)
                .build();
    }
}
