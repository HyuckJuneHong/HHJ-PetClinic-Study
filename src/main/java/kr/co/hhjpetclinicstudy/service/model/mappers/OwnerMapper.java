package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(target = "firstName", source = "create.firstName")
    @Mapping(target = "lastName", source = "create.lastName")
    @Mapping(target = "address", source = "create.address")
    @Mapping(target = "city", source = "create.city")
    @Mapping(target = "telephone", source = "create.telephone")
    Owner toOwnerEntity(OwnerReqDTO.CREATE create);

    @Mapping(target = "firstName", source = "owner.firstName")
    @Mapping(target = "lastName", source = "owner.lastName")
    @Mapping(target = "address", source = "owner.address")
    @Mapping(target = "city", source = "owner.city")
    @Mapping(target = "telephone", source = "owner.telephone")
    OwnerResDTO.READ toReadDto(Owner owner);
}
