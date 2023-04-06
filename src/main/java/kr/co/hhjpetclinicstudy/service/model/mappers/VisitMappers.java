package kr.co.hhjpetclinicstudy.service.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VisitMappers {

    VisitMappers INSTANCE = Mappers.getMapper(VisitMappers.class);

}
