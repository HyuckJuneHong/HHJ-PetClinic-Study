package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMappers {

    @Mapping(target = "firstName", source = "create.firstName")
    @Mapping(target = "lastName", source = "create.lastName")
    @Mapping(target = "vetSpecialties", source = "vetSpecialties")
    Vet toVetEntity(VetReqDTO.CREATE create, List<VetSpecialty> vetSpecialties);

    @Mapping(target = "firstName", source = "vet.firstName")
    @Mapping(target = "lastName", source = "vet.lastName")
    @Mapping(target = "specialtiesName", source = "specialtiesName")
    VetResDTO.READ toReadDto(Vet vet, List<String> specialtiesName);
}
