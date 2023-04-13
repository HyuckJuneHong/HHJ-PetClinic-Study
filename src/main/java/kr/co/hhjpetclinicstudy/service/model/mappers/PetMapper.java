package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.PetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.PetResDTO;
import kr.co.hhjpetclinicstudy.service.model.enums.PetType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(target = "petName", source = "create.petName")
    @Mapping(target = "birthDate", source = "create.birthDate")
    @Mapping(target = "petType", source = "create.petType", qualifiedByName = "toPetTypeEnum")
    @Mapping(target = "owner", source = "owner")
    Pet toPetEntity(PetReqDTO.CREATE create, Owner owner);

    @Mapping(target = "petName", source = "pet.petName")
    @Mapping(target = "petType", source = "pet.petType")
    @Mapping(target = "birthDate", source = "pet.birthDate")
    @Mapping(target = "ownerFirstName", source = "pet.owner.firstName")
    @Mapping(target = "ownerLastName", source = "pet.owner.lastName")
    @Mapping(target = "ownerTelephone", source = "pet.owner.telephone")
    PetResDTO.READ toReadDto(Pet pet);

    @Named("toPetTypeEnum")
    static PetType toPetTypeEnum(String petType) {

        return PetType.of(petType);
    }
}
