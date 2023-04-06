package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PetMappers {

    PetMappers INSTANCE = Mappers.getMapper(PetMappers.class);

    //PetReqDTO.CREATE, Owner Entity -> Pet Entity
    Pet toPetEntity(PetReqDTO.CREATE create, Owner owner);

    //Pet Entity -> PetResDTO.READ
    PetResDTO.READ toReadDto(Pet pet);
}
