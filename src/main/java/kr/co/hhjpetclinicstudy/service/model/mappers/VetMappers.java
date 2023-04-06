package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Vet;
import kr.co.hhjpetclinicstudy.persistence.entity.VetSpecialty;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VetReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.VetResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VetMappers {

    VetMappers INSTANCE = Mappers.getMapper(VetMappers.class);

    //VetReqDTO.CREATE, List<VetSpecialty> -> Vet Entity
    Vet toVetEntity(VetReqDTO.CREATE create, List<VetSpecialty> vetSpecialties);

    //Vet Entity, List<String> -> VetResDTO.READ DTO
    VetResDTO.READ toReadDto(Vet vet, List<String> specialtiesName);
}
