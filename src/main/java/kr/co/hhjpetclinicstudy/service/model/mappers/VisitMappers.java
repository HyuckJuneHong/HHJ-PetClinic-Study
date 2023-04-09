package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VisitResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VisitMappers {

    @Mapping(target = "visitDate", source = "create.visitDate")
    @Mapping(target = "description", source = "create.description")
    @Mapping(target = "pet", source = "pet")
    Visit toVisitEntity(VisitReqDTO.CREATE create, Pet pet);

    @Mapping(target = "visitDate", source = "visit.visitDate")
    @Mapping(target = "description", source = "visit.description")
    @Mapping(target = "petName", source = "visit.pet.petName")
    @Mapping(target = "petType", source = "visit.pet.petType")
    @Mapping(target = "petBirthDate", source = "visit.pet.birthDate")
    @Mapping(target = "ownerFirstName", source = "visit.pet.owner.firstName")
    @Mapping(target = "ownerLastName", source = "visit.pet.owner.lastName")
    @Mapping(target = "ownerTelephone", source = "visit.pet.owner.telephone")
    VisitResDTO.READ toReadDto(Visit visit);
}
