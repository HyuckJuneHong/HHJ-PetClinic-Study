package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Specialty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SpecialtyMapper {

    @Mapping(target = "specialtyName", source = "name")
    Specialty toSpecialtyEntity(String name);
}
