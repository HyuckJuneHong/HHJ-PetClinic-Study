package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpecialtyMappers {

    SpecialtyMappers INSTANCE = Mappers.getMapper(SpecialtyMappers.class);

    //String -> Specialty Entity
    Specialty toSpecialtyEntity(String name);
}
