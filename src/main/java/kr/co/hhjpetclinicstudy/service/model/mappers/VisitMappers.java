package kr.co.hhjpetclinicstudy.service.model.mappers;

import kr.co.hhjpetclinicstudy.persistence.entity.Pet;
import kr.co.hhjpetclinicstudy.persistence.entity.Visit;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.VisitReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VisitMappers {

    VisitMappers INSTANCE = Mappers.getMapper(VisitMappers.class);

    //VisitReqDTO.CREATE, Pet Entity -> Visit Entity
    Visit toVisitEntity(VisitReqDTO.CREATE create, Pet pet);
}
