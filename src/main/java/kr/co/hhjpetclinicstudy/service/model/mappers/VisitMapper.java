package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VisitMapper {

    @Mapping(target = "visitDate", source = "create.visitDate")
    @Mapping(target = "description", source = "create.description")
    @Mapping(target = "pet", source = "pet")
    @Mapping(target = "vet", source = "vet")
    @Mapping(target = "owner", source = "owner")
    Visit toVisitEntity(VisitReqDTO.CREATE create, Pet pet, Vet vet, Owner owner);

    @Mapping(target = "visitDate", source = "visit.visitDate")
    @Mapping(target = "petName", source = "visit.pet.petName")
    @Mapping(target = "petType", source = "visit.pet.petType")
    @Mapping(target = "ownerFirstName", source = "visit.pet.owner.firstName")
    @Mapping(target = "ownerLastName", source = "visit.pet.owner.lastName")
    VisitResDTO.READ toReadDto(Visit visit);

    @Mapping(target = "visitDate", source = "visit.visitDate")
    @Mapping(target = "description", source = "visit.description")
    @Mapping(target = "petName", source = "visit.pet.petName")
    @Mapping(target = "petType", source = "visit.pet.petType")
    @Mapping(target = "petBirthDate", source = "visit.pet.birthDate")
    @Mapping(target = "ownerFirstName", source = "visit.owner.firstName")
    @Mapping(target = "ownerLastName", source = "visit.owner.lastName")
    @Mapping(target = "ownerTelephone", source = "visit.owner.telephone")
    @Mapping(target = "vetFirstName", source = "visit.vet.firstName")
    @Mapping(target = "vetLastName", source = "visit.vet.lastName")
    VisitResDTO.READ_DETAIL toReadDetailDto(Visit visit);
}
