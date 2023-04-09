package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VetSpecialtyMappers {

    VetSpecialtyMappers INSTANCE = Mappers.getMapper(VetSpecialtyMappers.class);

    VetSpecialty toVetSepcialtyEntity(Specialty specialty, Vet vet);
}
