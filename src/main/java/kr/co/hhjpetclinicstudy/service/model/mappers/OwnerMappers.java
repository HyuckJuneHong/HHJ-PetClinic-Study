package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OwnerMappers {

    OwnerMappers INSTANCE = Mappers.getMapper(OwnerMappers.class);

    //OwnerReqDTO.CREATE -> Owner Entity
    Owner toOwnerEntity(OwnerReqDTO.CREATE create);

    //Owner Entity -> OwnerResDTO.READ
    OwnerResDTO.READ toReadDto(Owner owner);
}
