package kr.co.hhjpetclinicstudy.service.model.mapper;

import kr.co.hhjpetclinicstudy.persistence.entity.Owner;
import kr.co.hhjpetclinicstudy.service.model.dtos.request.OwnerReqDTO;
import kr.co.hhjpetclinicstudy.service.model.dtos.response.OwnerResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper를 사용하면서 느낀점
 * - 코드량을 매우 많이~~ 줄여준다는 장점을 느꼈다.
 *
 * 하지만,
 * - 모델이 단순하면 상관이 없는데, 매핑해야 하는 모델이 복잡하거나 서로 차이가 많이 나면... 머리속으로 생각을 좀 많이 해야 했다.
 * - 그래서 그냥 다 적어버림 ㅋㅋㅋ (@Mapping ㅠㅠ)
 * - 실행을 해봐야 오류를 찾을 수 있었다. (직접 작성할 땐 컴파일 시점에 오류를 잡을 수 있었다.)
 * - 수정 사항이 생기면 좀 귀찮았다. 에혀 ㅋㅋ 그래서 음? ㅋㅋㅋ이거 확실히 좋은거 맞지?
 */
@Mapper(componentModel = "spring")
public interface OwnerMapper {

    @Mapping(target = "firstName", source = "create.firstName")
    @Mapping(target = "lastName", source = "create.lastName")
    @Mapping(target = "address", source = "create.address")
    @Mapping(target = "city", source = "create.city")
    @Mapping(target = "telephone", source = "create.telephone")
    Owner toOwnerEntity(OwnerReqDTO.CREATE create);

    @Mapping(target = "firstName", source = "owner.firstName")
    @Mapping(target = "lastName", source = "owner.lastName")
    @Mapping(target = "address", source = "owner.address")
    @Mapping(target = "city", source = "owner.city")
    @Mapping(target = "telephone", source = "owner.telephone")
    OwnerResDTO.READ toReadDto(Owner owner);
}
