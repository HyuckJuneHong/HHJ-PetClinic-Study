package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VetSpecialtyMappers {

    @Mapping(target = "vet", source = "vet")
    @Mapping(target = "specialty", source = "specialty")
    VetSpecialty toVetSpecialtyEntity(Specialty specialty, Vet vet);
}
