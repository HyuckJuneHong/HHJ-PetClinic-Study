package kr.co.hhjpetclinicstudy.service.model.mapper;

import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMapper {

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    Vet toVetEntity(String firstName, String lastName);

    @Mapping(target = "firstName", source = "vet.firstName")
    @Mapping(target = "lastName", source = "vet.lastName")
    @Mapping(target = "specialtiesName", source = "specialtiesName")
    VetResDTO.READ toReadDto(Vet vet, List<String> specialtiesName);
}
